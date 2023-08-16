package executor.service.publisher.queue;

import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandler;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandlerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScenarioSourceQueueHandlerImplMockTest {
    private ConcurrentLinkedQueue<ScenarioDto> scenarios;
    private ScenarioSourceQueueHandler scenarioSourceQueueHandler;

    @BeforeEach
    public void setUp(){
        scenarios = Mockito.mock(ConcurrentLinkedQueue.class);
        scenarioSourceQueueHandler = new ScenarioSourceQueueHandlerImpl(scenarios);
    }

    @Test
    public void addTest(){
        ScenarioDto scenario = new ScenarioDto();
        scenarioSourceQueueHandler.add(scenario);
        verify(scenarios, times(1)).add(scenario);
    }

    @Test
    public void addAllTest(){
        List<ScenarioDto> scenariosList = List.of(new ScenarioDto(), new ScenarioDto(), new ScenarioDto());
        scenarioSourceQueueHandler.addAll(scenariosList);
        verify(scenarios, times(1)).addAll(scenariosList);
    }

    @Test
    public void pollTest(){
        ScenarioDto expectedScenario = new ScenarioDto();
        when(scenarios.poll()).thenReturn(expectedScenario);
        Optional<ScenarioDto> actualScenario = scenarioSourceQueueHandler.poll();
        verify(scenarios, times(1)).poll();
        assertTrue(actualScenario.isPresent());
        assertEquals(Optional.of(expectedScenario), actualScenario);
    }

    @Test
    public void removeAllTest(){
        ScenarioDto scenario1 = new ScenarioDto();
        ScenarioDto scenario2 = new ScenarioDto();
        ScenarioDto scenario3 = new ScenarioDto();
        List<ScenarioDto> expectedScenariosList = List.of(scenario1, scenario2, scenario3);
        when(scenarios.poll()).thenReturn(scenario1,scenario2, scenario3, null);
        List<ScenarioDto> actualScenariosList = scenarioSourceQueueHandler.removeAll();
        verify(scenarios, times(4)).poll();
        assertEquals(expectedScenariosList, actualScenariosList);
    }
}
