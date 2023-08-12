package executor.service.publisher.processing;

import executor.service.publisher.model.ScenarioDto;
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
        return Stream.generate(queueHandler::poll).flatMap(Optional::stream).limit(size).toList();
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
