package executor.service.publisher.queue.proxy;

import executor.service.publisher.annotation.Logged;
import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.queue.QueueHandler;
import lombok.RequiredArgsConstructor;

import java.util.*;
@Logged
@RequiredArgsConstructor
public class ProxySourceQueueHandlerImpl implements ProxySourceQueueHandler {
    private final QueueHandler<ProxyConfigHolder> handler;

    @Override
    public void add(ProxyConfigHolder proxy) {
        handler.add(proxy);
    }

    @Override
    public void addAll(List<ProxyConfigHolder> proxies) {
        handler.addAll(proxies);
    }

    @Override
    public Optional<ProxyConfigHolder> poll() {
        return handler.poll();
    }

    @Override
    public List<ProxyConfigHolder> removeAll() {
        return handler.removeAll();
    }

    @Override
    public List<ProxyConfigHolder> removeByCount(int size) {
        return handler.removeByCount(size);
    }

}
