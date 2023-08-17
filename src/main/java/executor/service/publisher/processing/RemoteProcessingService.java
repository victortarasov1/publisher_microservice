package executor.service.publisher.processing;

/**
 * A basic interface describing the process of loading data from remote sources and adding them to a queue.
 *
 * @param <T> the type of the data transfer object containing information about the custom remote source
 */
public interface RemoteProcessingService<T> {
    void loadFromDefaultRemoteSource();

    void loadFromCustomRemoteSource(T dto);
}
