package executor.service.publisher.source.okhttp;

import executor.service.publisher.exception.source.DataParsingException;
import executor.service.publisher.exception.source.okhttp.CallException;
import executor.service.publisher.exception.source.okhttp.EmptyResponseBodyException;
import executor.service.publisher.exception.source.okhttp.UnsuccessfulResponseException;
import okhttp3.Request;

import java.util.List;

/**
 * An interface for loading data using OkHttp.
 * Provides methods to load data from a given request and parse it into a list of specified type.
 */

public interface OkhttpLoader {
    /**
     * Loads data from the given OkHttp request and parses it into a list of the specified class type.
     *
     * @param request the OkHttp request to be executed
     * @param clazz   the class type to which the response data should be parsed
     * @param <T>     the type of objects in the resulting list
     * @return a list of parsed objects
     * @throws CallException                 if there is an issue making the HTTP call
     * @throws UnsuccessfulResponseException if the HTTP response is not successful
     * @throws EmptyResponseBodyException    if the HTTP response has an empty body
     * @throws DataParsingException          if there is an issue parsing the response data
     */
    <T> List<T> loadData(Request request, Class<T> clazz);
}
