package executor.service.processing.service.report;

import executor.service.collection.queue.report.ReportQueueHandler;
import executor.service.model.ScenarioReport;
import executor.service.processing.service.ProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportProcessingServiceImpl implements ReportProcessingService {

    private final ReportQueueHandler handler;

    @Override
    public void add(ScenarioReport element) {
        handler.add(element);
    }

    @Override
    public void addAll(List<ScenarioReport> elements) {
        handler.addAll(elements);
    }

    @Override
    public List<ScenarioReport> removeByCount(int count) {
        return handler.removeByCount(count);
    }

    @Override
    public Optional<ScenarioReport> poll() {
        return handler.poll();
    }

    @Override
    public List<ScenarioReport> removeAll() {
        return handler.removeAll();
    }
}
