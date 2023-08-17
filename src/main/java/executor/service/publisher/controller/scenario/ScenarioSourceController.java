package executor.service.publisher.controller.scenario;

import executor.service.publisher.controller.SourceController;
import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Component
@RequestMapping("/publisher/scenario")
public class ScenarioSourceController implements SourceController<ScenarioDto> {

    private final ScenarioSourceQueueHandler scenarios;

    public ScenarioSourceController(ScenarioSourceQueueHandler scenarios) {
        this.scenarios = scenarios;
    }

    @Override
    public void add(ScenarioDto scenarioDto) {
        scenarios.add(scenarioDto);
    }

    @Override
    public void addAll(List<ScenarioDto> scenarios) {
        this.scenarios.addAll(scenarios);
    }

    @Override
    public List<ScenarioDto> removeByCount(Integer size) {
        return scenarios.removeByCount(size);
    }

    @Override
    public Optional<ScenarioDto> poll() {
        return scenarios.poll();
    }

    @Override
    public List<ScenarioDto> removeAll() {
        return scenarios.removeAll();
    }
}
