package executor.service.validator;

import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
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
class HttpProxyValidator implements ProxyValidator {
    private static final String PROXY_CHECKER_URL = "http://httpbin.org/ip";

    @Override
    public boolean isValid(ProxyConfigHolder proxy) {
        OkHttpClient proxiedHttpClient = createProxiedHttpClient(proxy);
        Request request = getRequest(proxy.getProxyCredentials());
        return validateProxy(proxiedHttpClient, request);
    }

    @Override
    public String getType() {
        return "http";
    }

    private OkHttpClient createProxiedHttpClient(ProxyConfigHolder proxy) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(proxy.getProxyNetworkConfig().getHostname(), proxy.getProxyNetworkConfig().getPort());
        return new OkHttpClient.Builder().proxy(new Proxy(Proxy.Type.HTTP, inetSocketAddress)).build();
    }

    private Request getRequest(ProxyCredentials credentials) {
        Request.Builder builder = new Request.Builder().url(PROXY_CHECKER_URL);
        if (credentials != null)
            builder.header(PROXY_AUTHORIZATION, Credentials.basic(credentials.getUsername(), credentials.getPassword()));
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
