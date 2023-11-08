package executor.service.collection.queue.report;

import executor.service.collection.queue.QueueHandler;
import executor.service.logger.annotation.Logged;
import executor.service.model.ScenarioReport;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Logged
@RequiredArgsConstructor
public class ReportQueueHandlerImpl implements ReportQueueHandler {
    private final QueueHandler<ScenarioReport> handler;
    @Override
    public void add(ScenarioReport element) {
        handler.add(element);
    }

    @Override
    public void addAll(List<ScenarioReport> elements) {
        handler.addAll(elements);
    }

    @Override
    public Optional<ScenarioReport> poll() {
        return handler.poll();
    }

    @Override
    public List<ScenarioReport> removeAll() {
        return handler.removeAll();
    }

    @Override
    public List<ScenarioReport> removeByCount(int size) {
        return handler.removeByCount(size);
    }
}
