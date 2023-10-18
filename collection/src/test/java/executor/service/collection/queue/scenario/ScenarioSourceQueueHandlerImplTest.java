package executor.service.collection.queue.scenario;

import executor.service.collection.queue.QueueHandler;
import executor.service.model.Scenario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ScenarioSourceQueueHandlerImplTest {
    private QueueHandler<Scenario> basicHandler;
    private ScenarioSourceQueueHandler queueHandler;

    @BeforeEach
    public void setUp() {
        basicHandler = mock(QueueHandler.class);
        queueHandler = new ScenarioSourceQueueHandlerImpl(basicHandler);
    }

    @Test
    void testAdd() {
        Scenario scenario = new Scenario();
        queueHandler.add(scenario);
        verify(basicHandler, times(1)).add(eq(scenario));
    }

    @Test
    void testAddAll() {
        List<Scenario> scenarios = List.of(new Scenario(), new Scenario());
        queueHandler.addAll(scenarios);
        verify(basicHandler, times(1)).addAll(eq(scenarios));
    }

    @Test
    void testPoll() {
        queueHandler.poll();
        verify(basicHandler, times(1)).poll();
    }

    @Test
    void testRemoveAll() {
        queueHandler.removeAll();
        verify(basicHandler, times(1)).removeAll();
    }

    @Test
    void testRemoveByCount() {
        int size = 7;
        queueHandler.removeByCount(size);
        verify(basicHandler, times(1)).removeByCount(size);
    }
}