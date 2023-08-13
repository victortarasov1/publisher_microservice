package executor.service.publisher.processing.proxy;

import executor.service.publisher.exception.validator.UnknownProxyTypeException;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxySourceDto;
import executor.service.publisher.processing.ProcessingService;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.validation.ProxyValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ProxyProcessingService implements ProcessingService<ProxyConfigHolderDto> {
    private final Map<String, ProxyValidator> validators;
    private final QueueHandler<ProxyConfigHolderDto> queueHandler;

    private final ProxySourceDto defaultSource;

    public ProxyProcessingService(List<ProxyValidator> validators, QueueHandler<ProxyConfigHolderDto> queueHandler, ProxySourceDto defaultSource) {
        this.validators = new ConcurrentHashMap<>(validators.stream().collect(Collectors.toMap(ProxyValidator::getType, Function.identity())));
        this.queueHandler = queueHandler;
        this.defaultSource = defaultSource;
    }

    @Override
    public void add(ProxyConfigHolderDto dto) {
        ProxyValidator validator = Optional.ofNullable(validators.get(defaultSource.getProxyType()))
                .orElseThrow(() -> new UnknownProxyTypeException(defaultSource.getProxyType()));
        CompletableFuture.runAsync(() -> {
            if (validator.isValid(dto)) queueHandler.add(dto);
        });
    }

    @Override
    public void addAll(List<ProxyConfigHolderDto> dtoList) {
        dtoList.forEach(this::add);
    }

    @Override
    public List<ProxyConfigHolderDto> removeByCount(int size) {
        return Stream.generate(queueHandler::poll)
                .takeWhile(Optional::isPresent).limit(size)
                .flatMap(Optional::stream).toList();
    }

    @Override
    public Optional<ProxyConfigHolderDto> poll() {
        return queueHandler.poll();
    }

    @Override
    public List<ProxyConfigHolderDto> removeAll() {
        return queueHandler.removeAll();
    }

}
