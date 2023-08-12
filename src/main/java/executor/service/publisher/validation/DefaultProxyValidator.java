package executor.service.publisher.validation;

import executor.service.publisher.model.ProxyConfigHolderDto;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.net.InetSocketAddress;
import java.net.Proxy;


import static org.springframework.http.HttpHeaders.PROXY_AUTHORIZATION;

public class DefaultProxyValidator implements ProxyValidator {
    private final OkHttpClient okHttpClient;

    public DefaultProxyValidator(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public boolean isValid(ProxyConfigHolderDto dto, String proxyType) {
        OkHttpClient proxiedHttpClient = createProxiedHttpClient(dto, proxyType);
        Request request = getRequest(dto.getProxyCredentials().getPassword(), dto.getProxyCredentials().getUsername());
        return validateProxy(proxiedHttpClient, request);
    }

    private OkHttpClient createProxiedHttpClient(ProxyConfigHolderDto dto, String proxyType) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(dto.getProxyNetworkConfig().getHostname(), dto.getProxyNetworkConfig().getPort());
        Proxy.Type type = switch (proxyType) {
            case "http", "https" -> Proxy.Type.HTTP;
            case "socks" -> Proxy.Type.SOCKS;
            case "direct" -> Proxy.Type.DIRECT;
            default -> throw new RuntimeException();
        };
        return okHttpClient.newBuilder().proxy(new Proxy(type, inetSocketAddress)).build();
    }

    private Request getRequest(String password, String username) {
        Request.Builder builder = new Request.Builder().url("https://httpbin.org/ip");
        if (username != null && password != null)
            builder.header(PROXY_AUTHORIZATION, Credentials.basic(username, password));
        return builder.build();
    }

    private boolean validateProxy(OkHttpClient proxiedHttpClient, Request request) {
        try (Response response = proxiedHttpClient.newCall(request).execute()) {
            return response.isSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

}
