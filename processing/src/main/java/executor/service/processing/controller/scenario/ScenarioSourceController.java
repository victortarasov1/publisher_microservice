package executor.service.processing.controller.scenario;

import executor.service.model.Scenario;
import executor.service.processing.controller.SourceController;
import executor.service.processing.service.scenario.ScenarioProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Component
@RequestMapping("/publisher/scenario")
@RequiredArgsConstructor
@CrossOrigin("*")
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

}
