package executor.service.publisher.processing.scenario;

import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ScenarioProcessingServiceImpl implements ScenarioProcessingService {

    private final ScenarioSourceQueueHandler queueHandler;

    public ScenarioProcessingServiceImpl(ScenarioSourceQueueHandler queueHandler) {
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
        return queueHandler.removeByCount(size);
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
