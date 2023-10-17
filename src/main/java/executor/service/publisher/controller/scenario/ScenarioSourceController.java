package executor.service.publisher.controller.scenario;

import executor.service.publisher.controller.SourceController;
import executor.service.publisher.model.Scenario;
import executor.service.publisher.processing.scenario.ScenarioProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Component
@RequestMapping("/publisher/scenario")
@RequiredArgsConstructor
public class ScenarioSourceController implements SourceController<Scenario> {

    private final ScenarioProcessingService service;

    @Override
    public void add(Scenario scenario) {
        service.add(scenario);
    }

    @Override
    public void addAll(List<Scenario> scenarios) {
        service.addAll(scenarios);
    }

    @Override
    public List<Scenario> removeByCount(Integer size) {
        return service.removeByCount(size);
    }

    @Override
    public Optional<Scenario> poll() {
        return service.poll();
    }

    @Override
    public List<Scenario> removeAll() {
        return service.removeAll();
    }
}
