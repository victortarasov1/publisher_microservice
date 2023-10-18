package executor.service.processing.scenario;

import executor.service.model.Scenario;
import executor.service.collection.queue.scenario.ScenarioSourceQueueHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ScenarioProcessingServiceImplTest {
    private ScenarioSourceQueueHandler queueHandler;

    private ScenarioProcessingService service;

    @BeforeEach
    void setUp() {
        queueHandler = mock(ScenarioSourceQueueHandler.class);
        service = new ScenarioProcessingServiceImpl(queueHandler);
    }

    @Test
    void testAdd() {
        Scenario scenario = new Scenario();
        service.add(scenario);
        verify(queueHandler, times(1)).add(scenario);
    }

    @Test
    void testAddAll() {
        List<Scenario> scenarios = List.of(new Scenario(), new Scenario());
        service.addAll(scenarios);
        verify(queueHandler, times(1)).addAll(scenarios);
    }

    @Test
    void testRemoveByCount() {
        int count = 5;
        service.removeByCount(count);
        verify(queueHandler, times(1)).removeByCount(count);
    }


    @Test
    void testRemoveAll() {
        service.removeAll();
        verify(queueHandler, times(1)).removeAll();
    }

    @Test
    void testPoll() {
        Scenario scenario = new Scenario();
        when(queueHandler.poll()).thenReturn(Optional.of(scenario));
        Optional<Scenario> polledDto = service.poll();
        assertThat(polledDto).isEqualTo(Optional.of(scenario));
    }
}