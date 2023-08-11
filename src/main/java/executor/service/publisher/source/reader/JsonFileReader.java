package executor.service.publisher.source.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.publisher.exception.source.DataParsingException;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class JsonFileReader implements FileReader {

    private final ObjectMapper objectMapper;

    public JsonFileReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> List<T> readData(String path, Class<T> clazz) {
        try (InputStream inputStream = new FileInputStream(path)) {
            return objectMapper.readerForListOf(clazz).readValue(inputStream);
        } catch (IOException ex) {
            throw new DataParsingException(ex);
        }
    }
}