package executor.service.publisher.source.service;

import java.util.List;

/**
 * An interface for loading data of type {@code T} from remote sources using information provided by a {@code sourceDto} of type {@code D}.
 * This interface is designed to facilitate data extraction from external resources.
 *
 * @param <T> the type of data to be extracted
 * @param <D> the type of the source DTO containing necessary data for extraction
 */
public interface SourceService<T, D> {
    /**
     * Loads data of type {@code T} from the specified external source using the provided {@code sourceDto}.
     *
     * @param sourceDto the source DTO containing necessary data for extraction
     * @return a list of extracted data
     */
    List<T> loadData(D sourceDto);

    /**
     *
     * @return a string describing the type of remote data source
     */
    String getType();
}
