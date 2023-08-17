package executor.service.publisher.queue;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A basic thread-safe implementation of the QueueHandler interface using a ConcurrentLinkedQueue.
 * This class provides a foundational implementation of the queue handling functionality described in the interface.
 *
 * @param <T> the type of elements stored in the queue
 */
public class ThreadSafeQueueHandler<T> implements QueueHandler<T> {
    private final Queue<T> queue;

    public ThreadSafeQueueHandler() {
        this.queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void add(T element) {
        queue.add(element);
    }

    @Override
    public void addAll(List<T> elements) {
        queue.addAll(elements);
    }

    @Override
    public Optional<T> poll() {
        return Optional.ofNullable(queue.poll());
    }

    @Override
    public List<T> removeAll() {
        List<T> elements = new ArrayList<>();
        T removed = queue.poll();
        while (removed != null) {
            elements.add(removed);
            removed = queue.poll();
        }
        return elements;
    }

    @Override
    public List<T> removeByCount(int size) {
        List<T> elements = new ArrayList<>();
        T removed = queue.poll();
        while (removed != null) {
            elements.add(removed);
            if (elements.size() < 10) removed = queue.poll();
            else return elements;
        }
        return elements;
    }
}
