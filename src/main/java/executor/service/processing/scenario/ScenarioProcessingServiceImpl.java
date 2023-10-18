package executor.service.processing.scenario;

import executor.service.collection.queue.scenario.ScenarioSourceQueueHandler;
import executor.service.model.Scenario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class ScenarioProcessingServiceImpl implements ScenarioProcessingService {
    private final ScenarioSourceQueueHandler queueHandler;

    @Override
    public void add(Scenario scenario) {
        queueHandler.add(scenario);
    }

    @Override
    public void addAll(List<Scenario> scenarios) {
        queueHandler.addAll(scenarios);
    }

    @Override
    public List<Scenario> removeByCount(int count) {
        return queueHandler.removeByCount(count);
    }

    @Override
    public Optional<Scenario> poll() {
        return queueHandler.poll();
    }

    @Override
    public List<Scenario> removeAll() {
        return queueHandler.removeAll();
    }
}
