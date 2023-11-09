package executor.service.processing.service.report;

import executor.service.collection.queue.report.ReportQueueHandler;
import executor.service.model.ScenarioReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
