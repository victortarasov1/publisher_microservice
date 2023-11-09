package executor.service.processing.service;

import java.util.List;

public interface ProcessingService <T> {
    void add(T element);
    void addAll(List<T> elements);
}
