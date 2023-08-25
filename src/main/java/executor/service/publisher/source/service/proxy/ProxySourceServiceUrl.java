package executor.service.publisher.source.service.proxy;

import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.model.ProxyCredentials;
import executor.service.publisher.model.ProxyNetworkConfig;
import executor.service.publisher.model.ProxySource;
import executor.service.publisher.source.okhttp.OkhttpLoader;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProxySourceServiceUrl implements ProxySourceService {
    private final OkhttpLoader loader;

    public ProxySourceServiceUrl(OkhttpLoader loader) {
        this.loader = loader;
    }

    
    @Override
    public List<ProxyConfigHolder> loadData(ProxySource source) {
        Request request = new Request.Builder().url(source.getSource()).get().build();
        return loader.loadData(request, proxyDto.class).stream().map(proxyDto::createProxyConfigHolder).toList();
    }

    @Override
    public String getType() {
        return "url";
    }

    private record proxyDto(String username, String password, String ip, Integer port) {
        proxyDto {
            ip = ip.strip();
            if(username != null && password != null) {
                username = username.strip();
                password = password.strip();
            }
        }
        ProxyConfigHolder createProxyConfigHolder() {
            return new ProxyConfigHolder(new ProxyNetworkConfig(ip, port), new ProxyCredentials(username, password));
        }
    }
}
