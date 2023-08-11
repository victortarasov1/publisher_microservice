package executor.service.publisher.source;

import executor.service.publisher.exception.source.SourceException;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxyCredentialsDTO;
import executor.service.publisher.model.ProxyNetworkConfigDTO;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.source.okhttp.OkhttpLoader;
import jakarta.annotation.PostConstruct;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProxySourceServiceUrl implements SourceService {
    private final Request request;
    private final QueueHandler<ProxyConfigHolderDto> handler;
    private final OkhttpLoader loader;

    public ProxySourceServiceUrl(@Value("${source.proxy.url}") String url,
                                 OkhttpLoader loader, QueueHandler<ProxyConfigHolderDto> handler) {
        this.request = new Request.Builder().url(url).get().build();
        this.handler = handler;
        this.loader = loader;
    }

    @Override
    @PostConstruct
    public void loadData() {
        try {
            List<proxyDto> proxyDtoList = loader.loadData(request, proxyDto.class);
            List<ProxyConfigHolderDto> proxies = proxyDtoList.stream().map(proxyDto::createProxyConfigHolder).toList();
            handler.addAll(proxies);
        } catch (SourceException ignored) {}
    }

    private record proxyDto(String username, String password, String host, Integer port) {
        ProxyConfigHolderDto createProxyConfigHolder() {
            return new ProxyConfigHolderDto(new ProxyNetworkConfigDTO(host, port), new ProxyCredentialsDTO(username, password));
        }
    }
}
