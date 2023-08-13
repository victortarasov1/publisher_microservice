package executor.service.publisher.processing.scenario;

import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.processing.ProcessingService;
import executor.service.publisher.queue.QueueHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ScenarioProcessingService implements ProcessingService<ScenarioDto> {

    private final QueueHandler<ScenarioDto> queueHandler;

    public ScenarioProcessingService(QueueHandler<ScenarioDto> queueHandler) {
        this.queueHandler = queueHandler;
    }

    @Override
    public void add(ScenarioDto dto) {
        queueHandler.add(dto);
    }

    @Override
    public void addAll(List<ScenarioDto> dtoList) {
        queueHandler.addAll(dtoList);
    }

    @Override
    public List<ScenarioDto> removeByCount(int size) {
        return Stream.generate(queueHandler::poll)
                .takeWhile(Optional::isPresent).limit(size)
                .flatMap(Optional::stream).toList();
    }

    @Override
    public Optional<ScenarioDto> poll() {
        return queueHandler.poll();
    }

    @Override
    public List<ScenarioDto> removeAll() {
        return queueHandler.removeAll();
    }
}
