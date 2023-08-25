package executor.service.publisher.processing.proxy;

import executor.service.publisher.exception.validator.UnknownProxyTypeException;
import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.model.ProxySource;
import executor.service.publisher.queue.proxy.ProxySourceQueueHandler;
import executor.service.publisher.validation.ProxyValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProxyProcessingServiceImpl implements ProxyProcessingService {
    private final Map<String, ProxyValidator> validators;
    private final ProxySourceQueueHandler queueHandler;

    private final ProxySource defaultSource;

    public ProxyProcessingServiceImpl(List<ProxyValidator> validators, ProxySourceQueueHandler queueHandler, ProxySource defaultSource) {
        this.validators = new ConcurrentHashMap<>(validators.stream().collect(Collectors.toMap(ProxyValidator::getType, Function.identity())));
        this.queueHandler = queueHandler;
        this.defaultSource = defaultSource;
    }

    @Override
    public void add(ProxyConfigHolder proxy) {
        ProxyValidator validator = Optional.ofNullable(validators.get(defaultSource.getType()))
                .orElseThrow(() -> new UnknownProxyTypeException(defaultSource.getType()));
        CompletableFuture.runAsync(() -> {
            if (validator.isValid(proxy)) queueHandler.add(proxy);
        });
    }

    @Override
    public void addAll(List<ProxyConfigHolder> proxies) {
        proxies.forEach(this::add);
    }

    @Override
    public List<ProxyConfigHolder> removeByCount(int count) {
        return queueHandler.removeByCount(count);
    }

    @Override
    public Optional<ProxyConfigHolder> poll() {
        return queueHandler.poll();
    }

    @Override
    public List<ProxyConfigHolder> removeAll() {
        return queueHandler.removeAll();
    }
}
