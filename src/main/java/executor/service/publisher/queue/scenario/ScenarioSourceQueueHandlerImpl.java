package executor.service.publisher.queue.scenario;

import executor.service.publisher.annotation.Logged;
import executor.service.publisher.model.Scenario;
import executor.service.publisher.queue.QueueHandler;

import java.util.List;
import java.util.Optional;

@Logged
public class ScenarioSourceQueueHandlerImpl implements ScenarioSourceQueueHandler {
    private final QueueHandler<Scenario> handler;

    public ScenarioSourceQueueHandlerImpl(QueueHandler<Scenario> handler) {
        this.handler = handler;
    }

    @Override
    public void add(Scenario scenario) {
        handler.add(scenario);
    }

    @Override
    public void addAll(List<Scenario> scenarios) {
        handler.addAll(scenarios);
    }

    @Override
    public Optional<Scenario> poll() {
        return handler.poll();
    }

    @Override
    public List<Scenario> removeAll() {
        return handler.removeAll();
    }

    @Override
    public List<Scenario> removeByCount(int size) {
        return handler.removeByCount(size);
    }
}
