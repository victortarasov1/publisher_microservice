package executor.service.publisher.queue;

import executor.service.publisher.model.ProxyConfigHolderDto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class ProxySourceQueueHandler implements QueueHandler<ProxyConfigHolderDto> {
    private final Queue<ProxyConfigHolderDto> proxies = new ConcurrentLinkedQueue<>();

    @Override
    public void add(ProxyConfigHolderDto element) {
        proxies.add(element);
    }

    @Override
    public void addAll(List<ProxyConfigHolderDto> elements) {
        proxies.addAll(elements);
    }

    @Override
    public Optional<ProxyConfigHolderDto> poll() {
        return Optional.ofNullable(proxies.poll());
    }

    @Override
    public List<ProxyConfigHolderDto> removeAll() {
        List<ProxyConfigHolderDto> removedProxies = new ArrayList<>();
        ProxyConfigHolderDto removed = proxies.poll();
        while (removed != null) {
            removedProxies.add(removed);
            removed = proxies.poll();
        }
        return removedProxies;
    }
}
