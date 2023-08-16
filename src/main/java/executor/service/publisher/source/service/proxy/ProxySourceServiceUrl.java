package executor.service.publisher.source.service.proxy;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxyCredentialsDTO;
import executor.service.publisher.model.ProxyNetworkConfigDTO;
import executor.service.publisher.model.ProxySourceDto;
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
    public List<ProxyConfigHolderDto> loadData(ProxySourceDto sourceDto) {
        Request request = new Request.Builder().url(sourceDto.getProxySource()).get().build();
        return loader.loadData(request, proxyDto.class).stream().map(proxyDto::createProxyConfigHolder).toList();
    }

    @Override
    public String getType() {
        return "url";
    }

    private record proxyDto(String username, String password, String ip, Integer port) {
        ProxyConfigHolderDto createProxyConfigHolder() {
            return new ProxyConfigHolderDto(new ProxyNetworkConfigDTO(ip.strip(), port), new ProxyCredentialsDTO(username, password));
        }
    }
}
