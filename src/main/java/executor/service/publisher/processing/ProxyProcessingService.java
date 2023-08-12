package executor.service.publisher.processing;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxySourceDto;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.validation.ProxyValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
@Component
public class ProxyProcessingService implements ProcessingService<ProxyConfigHolderDto> {
    private final ProxyValidator validator;
    private final QueueHandler<ProxyConfigHolderDto> queueHandler;

    private final ProxySourceDto defaultSource;

    public ProxyProcessingService(ProxyValidator validator, QueueHandler<ProxyConfigHolderDto> queueHandler, ProxySourceDto defaultSource) {
        this.validator = validator;
        this.queueHandler = queueHandler;
        this.defaultSource = defaultSource;
    }

    @Override
    public void add(ProxyConfigHolderDto dto) {
        CompletableFuture.runAsync(() -> {
            if (validator.isValid(dto, defaultSource.getProxyType())) queueHandler.add(dto);
        });
    }

    @Override
    public void addAll(List<ProxyConfigHolderDto> dtoList) {
        dtoList.forEach(this::add);
    }

    @Override
    public List<ProxyConfigHolderDto> removeByCount(int size) {
        return Stream.generate(queueHandler::poll)
                .takeWhile(Optional::isPresent)
                .flatMap(Optional::stream)
                .limit(size).toList();
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
