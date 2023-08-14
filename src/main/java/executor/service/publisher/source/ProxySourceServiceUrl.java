package executor.service.publisher.source;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxyCredentialsDTO;
import executor.service.publisher.model.ProxyNetworkConfigDTO;
import executor.service.publisher.model.ProxySourceDto;
import executor.service.publisher.source.okhttp.OkhttpLoader;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProxySourceServiceUrl implements SourceService<ProxyConfigHolderDto, ProxySourceDto> {
    private final OkhttpLoader loader;

    public ProxySourceServiceUrl(OkhttpLoader loader) {
        this.loader = loader;
    }

    
    @Override
    public List<ProxyConfigHolderDto> loadData(ProxySourceDto dto) {
        Request request = new Request.Builder().url(dto.getProxySource()).get().build();
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
