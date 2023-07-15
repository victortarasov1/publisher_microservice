package executor.service.publisher.queue;

import executor.service.publisher.model.ProxyConfigHolderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ProxySourceQueueHandlerThreadSafetyTest {
    private static final int THREAD_COUNT = 8;
    private static final int ELEMENT_COUNT = 100;

    private QueueHandler<ProxyConfigHolderDto> queueHandler;
    private CountDownLatch latch;
    @BeforeEach
    public void setUp() {
        queueHandler = new ProxySourceQueueHandler(new ConcurrentLinkedQueue<>());
        latch = new CountDownLatch(THREAD_COUNT);
    }

    @Test
    public void testAddMethodThreadSafety() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        Runnable addTask = () -> {
            queueHandler.add(new ProxyConfigHolderDto());
            latch.countDown();
        };
        IntStream.range(0, THREAD_COUNT).forEach(v -> executorService.submit(addTask));
        latch.await();
        assertEquals(THREAD_COUNT, queueHandler.removeAll().size());
    }

    @Test
    public void testAddAllMethodThreadSafety() throws InterruptedException {
        List<ProxyConfigHolderDto> elements = IntStream.range(0, ELEMENT_COUNT).boxed().map(v -> new ProxyConfigHolderDto()).toList();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        Runnable addAllTask = () -> {
            queueHandler.addAll(elements);
            latch.countDown();
        };
        IntStream.range(0, THREAD_COUNT).forEach(v -> executorService.submit(addAllTask));
        latch.await();
        assertEquals(THREAD_COUNT * ELEMENT_COUNT, queueHandler.removeAll().size());
    }

    @Test
    public void testPollMethodThreadSafety() throws InterruptedException {
        IntStream.range(0, ELEMENT_COUNT).forEach(i -> queueHandler.add(new ProxyConfigHolderDto()));
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        Runnable pollTask = () -> {
            queueHandler.poll();
            latch.countDown();
        };
        IntStream.range(0, THREAD_COUNT).forEach(v -> executorService.submit(pollTask));
        latch.await();
        assertEquals(ELEMENT_COUNT - THREAD_COUNT, queueHandler.removeAll().size());
    }

    @Test
    public void testRemoveAllMethodThreadSafety() throws InterruptedException {
        IntStream.range(0, ELEMENT_COUNT).forEach(i -> queueHandler.add(new ProxyConfigHolderDto()));
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        AtomicInteger resultSize = new AtomicInteger(0);
        Runnable removeAllTask = () -> {
            resultSize.addAndGet(queueHandler.removeAll().size());
            latch.countDown();
        };
        IntStream.range(0, THREAD_COUNT).forEach(v -> executorService.submit(removeAllTask));
        latch.await();
        assertEquals(ELEMENT_COUNT, resultSize.get());
    }
}