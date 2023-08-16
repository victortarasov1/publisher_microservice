package executor.service.publisher.queue;

import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandler;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandlerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScenarioSourceQueueHandlerImplThreadSafetyTest {
    private ScenarioSourceQueueHandler scenarioSourceQueueHandler;
    private static final int THREAD_COUNT = 8;
    private static final int ELEMENT_COUNT = 200;
    private CountDownLatch countDownLatch;
    private ExecutorService executorService;

    @BeforeEach
    public void setUp(){
        scenarioSourceQueueHandler = new ScenarioSourceQueueHandlerImpl(new ConcurrentLinkedQueue<>());
        countDownLatch = new CountDownLatch(THREAD_COUNT);
        executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    @Test
    public void addTest() throws InterruptedException {
        Runnable addRunnableTask = () -> {
            scenarioSourceQueueHandler.add(new ScenarioDto());
            countDownLatch.countDown();
        };
        IntStream.range(0, THREAD_COUNT).forEach(v -> executorService.submit(addRunnableTask));
        countDownLatch.await();
        assertEquals(THREAD_COUNT, scenarioSourceQueueHandler.removeAll().size());
    }

    @Test
    public void addAllTest() throws InterruptedException {
        List<ScenarioDto> elements = IntStream.range(0, ELEMENT_COUNT).boxed().map(v -> new ScenarioDto()).toList();
        Runnable addAllRunnableTask = () -> {
            scenarioSourceQueueHandler.addAll(elements);
            countDownLatch.countDown();
        };
        IntStream.range(0, THREAD_COUNT).forEach(v -> executorService.submit(addAllRunnableTask));
        countDownLatch.await();
        assertEquals(THREAD_COUNT * ELEMENT_COUNT, scenarioSourceQueueHandler.removeAll().size());
    }

    @Test
    public void pollTest() throws InterruptedException {
        IntStream.range(0, ELEMENT_COUNT).forEach
                (i -> scenarioSourceQueueHandler.add(new ScenarioDto()));
        Runnable pollRunnableTask = () -> {
            scenarioSourceQueueHandler.poll();
            countDownLatch.countDown();
        };
        IntStream.range(0, THREAD_COUNT).forEach(v -> executorService.submit(pollRunnableTask));
        countDownLatch.await();
        assertEquals(ELEMENT_COUNT - THREAD_COUNT, scenarioSourceQueueHandler.removeAll().size());
    }

    @Test
    public void removeAllTest() throws InterruptedException {
        IntStream.range(0, ELEMENT_COUNT).forEach
                (i -> scenarioSourceQueueHandler.add(new ScenarioDto()));
        AtomicInteger resultSize = new AtomicInteger(0);
        Runnable removeAllRunnableTask = () -> {
            resultSize.addAndGet(scenarioSourceQueueHandler.removeAll().size());
            countDownLatch.countDown();
        };
        IntStream.range(0, THREAD_COUNT).forEach(v -> executorService.submit(removeAllRunnableTask));
        countDownLatch.await();
        assertEquals(ELEMENT_COUNT, resultSize.get());
        assertEquals(0, scenarioSourceQueueHandler.removeAll().size());
    }
}
