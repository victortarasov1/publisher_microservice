package executor.service.publisher.controller.proxy;

import executor.service.publisher.controller.SourceController;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.processing.ProcessingService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Component
@RequestMapping("/publisher/proxy")
public class ProxySourceController implements SourceController<ProxyConfigHolderDto> {

    private final ProcessingService<ProxyConfigHolderDto> service;

    public ProxySourceController(ProcessingService<ProxyConfigHolderDto> service) {
        this.service = service;
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
        return service.removeByCount(size);
    }

    @Override
    public Optional<ProxyConfigHolderDto> poll() {
        return service.poll();
    }

    @Override
    public List<ProxyConfigHolderDto> removeAll() {
        return service.removeAll();
    }
}