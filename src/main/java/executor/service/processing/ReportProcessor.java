package executor.service.processing;

import executor.service.model.ScenarioReport;

import java.util.List;

public interface ReportProcessor {
    List<ScenarioReport> findByScenarioId(String scenarioId);

    List<ScenarioReport> findAll();
}
