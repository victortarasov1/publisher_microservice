package executor.service.processing.service.proxy;

import executor.service.model.ProxyConfigHolder;
import executor.service.queue.producer.proxy.ProxyQueueProducer;
import executor.service.source.exception.UnknownSourceServiceException;
import executor.service.source.model.ProxySource;
import executor.service.source.service.proxy.ProxySourceService;
import executor.service.validator.ProxyValidator;
import executor.service.validator.exception.UnknownProxyTypeException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProxyRemoteProcessingServiceImpl implements ProxyRemoteProcessingService {
    private final Map<String, ProxyValidator> validators;
    private final Map<String, ProxySourceService> sourceServices;
    private final ProxySource defaultSource;
    private final ProxyQueueProducer producer;


    public ProxyRemoteProcessingServiceImpl(List<ProxyValidator> validators, List<ProxySourceService> sourceServices,
                                            ProxySource defaultSource, ProxyQueueProducer producer) {
        this.validators = new ConcurrentHashMap<>(validators.stream().collect(Collectors.toMap(ProxyValidator::getType, Function.identity())));
        this.sourceServices = new ConcurrentHashMap<>(sourceServices.stream().collect(Collectors.toMap(ProxySourceService::getType, Function.identity())));
        this.defaultSource = defaultSource;
        this.producer = producer;
    }

    @Override
    public void loadFromDefaultRemoteSource() {
        loadFromCustomRemoteSource(defaultSource);
    }

    @Override
    public void loadFromCustomRemoteSource(ProxySource source) {
        ProxySourceService service = Optional.ofNullable(sourceServices.get(source.getStorage()))
                .orElseThrow(() -> new UnknownSourceServiceException(source.getStorage()));
        ProxyValidator validator = Optional.ofNullable(validators.get(source.getType()))
                .orElseThrow(() -> new UnknownProxyTypeException(source.getType()));
        Consumer<ProxyConfigHolder> asyncValidate = v -> CompletableFuture.runAsync(() -> {
            if (validator.isValid(v)) producer.add(v);
        });
        CompletableFuture.supplyAsync(() -> service.loadData(source)).thenAcceptAsync(lst -> lst.forEach(asyncValidate));
    }
}
