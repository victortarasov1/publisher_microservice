package executor.service.publisher.queue.scenario;

import executor.service.publisher.model.ScenarioDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class ScenarioSourceQueueHandlerImpl implements ScenarioSourceQueueHandler {
    private final Queue<ScenarioDto> scenarios;

    public ScenarioSourceQueueHandlerImpl(Queue<ScenarioDto> scenarios) {
        this.scenarios = scenarios;
    }

    @Override
    public void add(ScenarioDto element) {
        scenarios.add(element);
    }

    @Override
    public void addAll(List<ScenarioDto> elements) {
        scenarios.addAll(elements);
    }

    @Override
    public Optional<ScenarioDto> poll() {
        return Optional.ofNullable(scenarios.poll());
    }

    @Override
    public List<ScenarioDto> removeAll() {
        List<ScenarioDto> removedScenarios = new ArrayList<>();
        ScenarioDto removedScenario = scenarios.poll();
        while (removedScenario != null){
            removedScenarios.add(removedScenario);
            removedScenario = scenarios.poll();
        }
        return removedScenarios;
    }
}
