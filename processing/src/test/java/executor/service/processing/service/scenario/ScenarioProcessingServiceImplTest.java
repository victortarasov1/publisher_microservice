package executor.service.processing.service.scenario;

import executor.service.model.Scenario;
import executor.service.queue.producer.scenario.ScenarioQueueProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ScenarioProcessingServiceImplTest {
    private ScenarioQueueProducer producer;

    private ScenarioProcessingService service;

    @BeforeEach
    void setUp() {
        producer = Mockito.mock(ScenarioQueueProducer.class);
        service = new ScenarioProcessingServiceImpl(producer);
    }

    @Test
    void testAdd() {
        Scenario scenario = new Scenario("some id", "some name", "some site", List.of());
        service.add(scenario);
        verify(producer, times(1)).add(scenario);
    }

    @Test
    void testAddAll() {
        List<Scenario> scenarios = List.of(new Scenario("some id","some name", "some site", List.of()),
                new Scenario("some id","some name", "some site", List.of()));
        service.addAll(scenarios);
        verify(producer, times(2)).add(any(Scenario.class));
    }
}