package executor.service.publisher.queue;

import java.util.List;
import java.util.Optional;

/**
 * A basic interface for a queue data structure that defines methods for adding and removing elements in a queue.
 *
 * @param <T> the type of elements stored in the queue
 */
public interface QueueHandler<T> {
    void add(T element);

    void addAll(List<T> elements);

    Optional<T> poll();

    List<T> removeAll();

    /**
     * Removes elements from the queue up to the specified count and returns them in a list.
     * The method continues polling elements from the queue until the desired count is reached
     * or until the queue is exhausted.
     *
     * @param size the maximum number of elements to be removed
     * @return a list of removed elements; the list size may be less than the specified count
     */
    List<T> removeByCount(int size);
}
