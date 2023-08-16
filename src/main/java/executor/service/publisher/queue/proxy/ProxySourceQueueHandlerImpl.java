package executor.service.publisher.queue.proxy;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;

import java.util.*;

public class ProxySourceQueueHandlerImpl implements ProxySourceQueueHandler {
    private final QueueHandler<ProxyConfigHolderDto> handler;

    public ProxySourceQueueHandlerImpl(QueueHandler<ProxyConfigHolderDto> handler) {
        this.handler = handler;
    }

    @Override
    public void add(ProxyConfigHolderDto element) {
        handler.add(element);
    }

    @Override
    public void addAll(List<ProxyConfigHolderDto> elements) {
        handler.addAll(elements);
    }

    @Override
    public Optional<ProxyConfigHolderDto> poll() {
        return handler.poll();
    }

    @Override
    public List<ProxyConfigHolderDto> removeAll() {
        return handler.removeAll();
    }

    @Override
    public List<ProxyConfigHolderDto> removeByCount(int size) {
        return handler.removeByCount(size);
    }

}
