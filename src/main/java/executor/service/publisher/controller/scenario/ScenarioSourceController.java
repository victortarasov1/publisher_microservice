package executor.service.publisher.controller.scenario;

import executor.service.publisher.controller.SourceController;
import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.processing.ProcessingService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Component
@RequestMapping("/publisher/scenario")
public class ScenarioSourceController implements SourceController<ScenarioDto> {

    private final ProcessingService<ScenarioDto> service;

    public ScenarioSourceController(ProcessingService<ScenarioDto> service) {
        this.service = service;
    }

    @Override
    public void add(ScenarioDto scenarioDto) {
        service.add(scenarioDto);
    }

    @Override
    public void addAll(List<ScenarioDto> scenarios) {
        service.addAll(scenarios);
    }

    @Override
    public List<ScenarioDto> removeByCount(Integer size) {
        return service.removeByCount(size);
    }

    @Override
    public Optional<ScenarioDto> poll() {
        return service.poll();
    }

    @Override
    public List<ScenarioDto> removeAll() {
        return service.removeAll();
    }
}
