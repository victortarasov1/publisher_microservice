package executor.service.publisher.processing.scenario;

import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class ScenarioProcessingServiceImpl implements ScenarioProcessingService {
    private final ScenarioSourceQueueHandler queueHandler;

    public ScenarioProcessingServiceImpl(ScenarioSourceQueueHandler queueHandler) {
        this.queueHandler = queueHandler;
    }

    @Override
    public void add(ScenarioDto element) {
        queueHandler.add(element);
    }

    @Override
    public void addAll(List<ScenarioDto> elements) {
        queueHandler.addAll(elements);
    }

    @Override
    public List<ScenarioDto> removeByCount(int count) {
        return queueHandler.removeByCount(count);
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
