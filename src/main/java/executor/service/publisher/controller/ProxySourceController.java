package executor.service.publisher.controller;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Component
@RequestMapping("/publisher/proxy")
public class ProxySourceController implements SourceController<ProxyConfigHolderDto> {

    private final QueueHandler<ProxyConfigHolderDto> proxyHandler;

    public ProxySourceController(QueueHandler<ProxyConfigHolderDto> proxyHandler) {
        this.proxyHandler = proxyHandler;
    }

    @Override
    public void add(ProxyConfigHolderDto proxy) {
        proxyHandler.add(proxy);
    }

    @Override
    public void addAll(List<ProxyConfigHolderDto> proxyList) {
        proxyHandler.addAll(proxyList);
    }

    @Override
    public Optional<ProxyConfigHolderDto> poll() {
        return proxyHandler.poll();
    }

    @Override
    public List<ProxyConfigHolderDto> removeAll() {
        return proxyHandler.removeAll();
    }
}