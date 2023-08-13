package executor.service.publisher.processing;

public interface RemoteProcessingService<T> {
    void loadFromDefaultRemoteSource();

    void loadFromCustomRemoteSource(T dto);
}
