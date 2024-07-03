package executor.service.processing;

import executor.service.model.ScenarioReport;
import executor.service.repository.ScenarioReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportProcessorImpl implements ReportProcessor {
    private final ScenarioReportRepository repository;
    @Override
    public List<ScenarioReport> findByScenarioId(String scenarioId) {
        return repository.findByScenarioId(scenarioId);
    }

    @Override
    public List<ScenarioReport> findAll() {
        return repository.findAll();
    }
}
