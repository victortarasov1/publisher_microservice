package executor.service.source.reader;

import executor.service.source.exception.DataParsingException;

import java.util.List;

/**
 * An interface for reading data from a file and parsing it into a list of specified type.
 */
public interface FileReader {
    /**
     * Reads data from the specified file path and parses it into a list of the specified class type.
     *
     * @param path  the path to the file to be read
     * @param clazz the class type to which the file data should be parsed
     * @param <T>   the type of objects in the resulting list
     * @return a list of parsed objects
     * @throws DataParsingException if there is an issue parsing the file data
     */
    <T> List<T> readData(String path, Class<T> clazz);
}
