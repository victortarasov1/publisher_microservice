package executor.service.processing.service.proxy;

import executor.service.model.ProxyConfigHolder;
import executor.service.queue.producer.proxy.ProxyQueueProducer;
import executor.service.source.model.ProxySource;
import executor.service.validator.ProxyValidator;
import executor.service.validator.exception.UnknownProxyTypeException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProxyProcessingServiceImpl implements ProxyProcessingService {
    private final Map<String, ProxyValidator> validators;
    private final ProxyQueueProducer producer;

    private final ProxySource defaultSource;

    public ProxyProcessingServiceImpl(List<ProxyValidator> validators, ProxyQueueProducer producer, ProxySource defaultSource) {
        this.validators = new ConcurrentHashMap<>(validators.stream().collect(Collectors.toMap(ProxyValidator::getType, Function.identity())));
        this.producer = producer;
        this.defaultSource = defaultSource;
    }

    @Override
    public void add(ProxyConfigHolder proxy) {
        ProxyValidator validator = Optional.ofNullable(validators.get(defaultSource.getType()))
                .orElseThrow(() -> new UnknownProxyTypeException(defaultSource.getType()));
        CompletableFuture.runAsync(() -> {
            if (validator.isValid(proxy)) producer.add(proxy);
        });
    }

    @Override
    public void addAll(List<ProxyConfigHolder> proxies) {
        proxies.forEach(this::add);
    }

}
