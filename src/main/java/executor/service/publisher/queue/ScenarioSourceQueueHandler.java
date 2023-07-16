package executor.service.publisher.queue;

import executor.service.publisher.model.ScenarioDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class ScenarioSourceQueueHandler implements QueueHandler<ScenarioDto>{
    private final Queue<ScenarioDto> scenarios;

    public ScenarioSourceQueueHandler(Queue<ScenarioDto> scenarios) {
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
