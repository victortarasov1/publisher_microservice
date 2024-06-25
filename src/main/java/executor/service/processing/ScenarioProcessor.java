package executor.service.processing;

import executor.service.model.Scenario;

import java.util.List;

public interface ScenarioProcessor {
    void add(Scenario scenario);
    void update(Scenario scenario);
    void delete(String scenarioId);
    Scenario get(String scenarioId);
    void execute(String scenarioId);

    List<Scenario> getAll();
}
