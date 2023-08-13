package executor.service.publisher.processing;

import java.util.List;
import java.util.Optional;

public interface ProcessingService<T> {
    void add(T dto);
    void addAll(List<T> dtoList);

    List<T> removeByCount(int size);

    Optional<T> poll();

    List<T> removeAll();

}
