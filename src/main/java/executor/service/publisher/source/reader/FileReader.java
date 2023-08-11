package executor.service.publisher.source.reader;

import java.util.List;

public interface FileReader {
    <T> List<T> readData(String path, Class<T> clazz);
}
