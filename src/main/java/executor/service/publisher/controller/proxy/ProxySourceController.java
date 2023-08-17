package executor.service.publisher.controller.proxy;

import executor.service.publisher.controller.SourceController;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.processing.proxy.ProxyProcessingService;
import executor.service.publisher.queue.proxy.ProxySourceQueueHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Component
@RequestMapping("/publisher/proxy")
public class ProxySourceController implements SourceController<ProxyConfigHolderDto> {

    private final ProxyProcessingService service;
    private final ProxySourceQueueHandler proxies;

    public ProxySourceController(ProxyProcessingService service, ProxySourceQueueHandler proxies) {
        this.service = service;
        this.proxies = proxies;
    }

    @Override
    public void add(ProxyConfigHolderDto proxy) {
        service.add(proxy);
    }

    @Override
    public void addAll(List<ProxyConfigHolderDto> proxyList) {
        service.addAll(proxyList);
    }

    @Override
    public List<ProxyConfigHolderDto> removeByCount(Integer size) {
        return proxies.removeByCount(size);
    }

    @Override
    public Optional<ProxyConfigHolderDto> poll() {
        return proxies.poll();
    }

    @Override
    public List<ProxyConfigHolderDto> removeAll() {
        return proxies.removeAll();
    }
}