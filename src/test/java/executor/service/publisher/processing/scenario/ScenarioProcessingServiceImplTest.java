package executor.service.publisher.processing.scenario;

import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandler;
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
        ScenarioDto scenarioDto = new ScenarioDto();
        service.add(scenarioDto);
        verify(queueHandler, times(1)).add(scenarioDto);
    }

    @Test
    void testAddAll() {
        List<ScenarioDto> scenarios = List.of(new ScenarioDto(), new ScenarioDto());
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
        ScenarioDto scenario = new ScenarioDto();
        when(queueHandler.poll()).thenReturn(Optional.of(scenario));
        Optional<ScenarioDto> polledDto = service.poll();
        assertThat(polledDto).isEqualTo(Optional.of(scenario));
    }
}