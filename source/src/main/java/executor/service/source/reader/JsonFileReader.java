package executor.service.source.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.source.exception.DataParsingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonFileReader implements FileReader {

    private final ObjectMapper objectMapper;
    @Override
    public <T> List<T> readData(String path, Class<T> clazz) {
        try (InputStream inputStream = new FileInputStream(path)) {
            return objectMapper.readerForListOf(clazz).readValue(inputStream);
        } catch (IOException ex) {
            throw new DataParsingException(ex);
        }
    }
}