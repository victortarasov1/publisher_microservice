package executor.service.publisher.controller;

import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.queue.QueueHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Component
@RequestMapping("/publisher/scenario")
public class ScenarioSourceController implements SourceController<ScenarioDto> {

    private final QueueHandler<ScenarioDto> handler;

    public ScenarioSourceController(QueueHandler<ScenarioDto> handler) {
        this.handler = handler;
    }

    @Override
    public void add(ScenarioDto scenarioDto) {
        handler.add(scenarioDto);
    }

    @Override
    public void addAll(List<ScenarioDto> scenarios) {
        handler.addAll(scenarios);
    }

    @Override
    public Optional<ScenarioDto> poll() {
        return handler.poll();
    }

    @Override
    public List<ScenarioDto> removeAll() {
        return handler.removeAll();
    }
}
