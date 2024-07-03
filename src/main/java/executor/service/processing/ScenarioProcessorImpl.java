package executor.service.processing;

import executor.service.exception.ScenarioWasNotFoundException;
import executor.service.model.Scenario;
import executor.service.repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScenarioProcessorImpl implements ScenarioProcessor {
    private final ScenarioRepository repository;
    private final StreamBridge streamBridge;
    @Override
    public void add(Scenario scenario) {
        repository.save(scenario);
    }

    @Override
    public void update(Scenario scenario) {
        var updated = repository.findById(scenario.getId()).orElseThrow(() -> new ScenarioWasNotFoundException(scenario.getId()));
        updated.setSite(scenario.getSite());
        updated.setName(scenario.getName());
    }

    @Override
    public void delete(String scenarioId) {
        var deleted = repository.findById(scenarioId).orElseThrow(() -> new ScenarioWasNotFoundException(scenarioId));
        repository.delete(deleted);
    }

    @Override
    public Scenario get(String scenarioId) {
        return repository.findById(scenarioId).orElseThrow(() -> new ScenarioWasNotFoundException(scenarioId));
    }

    @Override
    public void execute(String scenarioId) {
        var scenario = repository.findById(scenarioId).orElseThrow(() -> new ScenarioWasNotFoundException(scenarioId));
        streamBridge.send("send-scenario", scenario);
    }

    @Override
    public List<Scenario> getAll() {
        return repository.findAll();
    }
}
