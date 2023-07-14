package executor.service.publisher.queue;

import java.util.List;
import java.util.Optional;

public interface QueueHandler<T> {
    void add(T element);

    void addAll(List<T> elements);

    Optional<T> poll();

    List<T> removeAll();
}
