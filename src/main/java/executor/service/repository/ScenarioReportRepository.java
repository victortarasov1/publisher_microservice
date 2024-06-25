package executor.service.repository;

import executor.service.model.ScenarioReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScenarioReportRepository extends JpaRepository<ScenarioReport, String> {
    List<ScenarioReport> findByScenarioId(String scenarioId);
}
