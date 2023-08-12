package executor.service.publisher.validation;

import executor.service.publisher.exception.validator.UnknownProxyTypeException;
import executor.service.publisher.model.ProxyConfigHolderDto;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;


import static org.springframework.http.HttpHeaders.PROXY_AUTHORIZATION;

@Component
public class HttpProxyValidator implements ProxyValidator {
    private final OkHttpClient okHttpClient;

    public HttpProxyValidator(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public boolean isValid(ProxyConfigHolderDto dto) {
        OkHttpClient proxiedHttpClient = createProxiedHttpClient(dto);
        Request request = getRequest(dto.getProxyCredentials().getPassword(), dto.getProxyCredentials().getUsername());
        return validateProxy(proxiedHttpClient, request);
    }

    @Override
    public String getType() {
        return "http";
    }

    private OkHttpClient createProxiedHttpClient(ProxyConfigHolderDto dto) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(dto.getProxyNetworkConfig().getHostname(), dto.getProxyNetworkConfig().getPort());
        return okHttpClient.newBuilder().proxy(new Proxy(Proxy.Type.HTTP, inetSocketAddress)).build();
    }

    private Request getRequest(String password, String username) {
        Request.Builder builder = new Request.Builder().url("http://httpbin.org/ip");
        if (username != null && password != null)
            builder.header(PROXY_AUTHORIZATION, Credentials.basic(username, password));
        return builder.build();
    }

    private boolean validateProxy(OkHttpClient proxiedHttpClient, Request request) {
        try (Response response = proxiedHttpClient.newCall(request).execute()) {
            return response.isSuccessful();
        } catch (IOException ex) {
            return false;
        }
    }

}
