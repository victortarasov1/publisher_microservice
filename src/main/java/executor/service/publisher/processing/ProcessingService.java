package executor.service.publisher.processing;

import java.util.List;
import java.util.Optional;

public interface ProcessingService <T> {
    void add(T element);
    void addAll(List<T> elements);
    List<T> removeByCount(int count);
    Optional<T> poll();
    List<T> removeAll();
}
