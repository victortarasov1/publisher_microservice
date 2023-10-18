package executor.service.source.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import executor.service.exception.source.DataParsingException;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;
import executor.service.model.Scenario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JsonFileReaderTest {

    private FileReader reader;

    @BeforeEach
    void setUp() {
        reader = new JsonFileReader(new ObjectMapper());
    }

    @Test
    void testReadData() {
        String path = "src/test/resources/testProxy.json";
        List<ProxyConfigHolder> expected = List.of(
                new ProxyConfigHolder(new ProxyNetworkConfig("host_0", 0), new ProxyCredentials("user_0", "000")),
                new ProxyConfigHolder(new ProxyNetworkConfig("host_1", 1), new ProxyCredentials("user_1", "111")),
                new ProxyConfigHolder(new ProxyNetworkConfig("host_2", 2), new ProxyCredentials("user_2", "222"))
        );
        List<ProxyConfigHolder> result = reader.readData(path, ProxyConfigHolder.class);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testReadData_whenCantMap_shouldThrowDataParsingExceptionCausedByUnrecognizedPropertyException() {
        String path = "src/test/resources/testProxy.json";
        assertThatThrownBy(() -> reader.readData(path, Scenario.class))
                .isInstanceOf(DataParsingException.class)
                .cause().isInstanceOf(UnrecognizedPropertyException.class);
    }

    @Test
    void testReadData_whenBadFile_shouldThrowDataParsingException() {
        String path = "src/test/resources/badProxy.json";
        assertThatThrownBy(() -> reader.readData(path, ProxyConfigHolder.class))
                .isInstanceOf(DataParsingException.class);
    }

    @Test
    void testReadData_whenFileNotFound_shouldThrowDataParsingExceptionCausedByFileNotfoundException() {
        String path = "src/test/resources/abracadabra.json";
        assertThatThrownBy(() -> reader.readData(path, ProxyConfigHolder.class))
                .isInstanceOf(DataParsingException.class)
                .cause().isInstanceOf(FileNotFoundException.class);
    }
}