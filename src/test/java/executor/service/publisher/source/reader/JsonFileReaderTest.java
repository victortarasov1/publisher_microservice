package executor.service.publisher.source.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import executor.service.publisher.exception.source.DataParsingException;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxyCredentialsDTO;
import executor.service.publisher.model.ProxyNetworkConfigDTO;
import executor.service.publisher.model.ScenarioDto;
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
        List<ProxyConfigHolderDto> expected = List.of(
                new ProxyConfigHolderDto(new ProxyNetworkConfigDTO("host_0", 0), new ProxyCredentialsDTO("user_0", "000")),
                new ProxyConfigHolderDto(new ProxyNetworkConfigDTO("host_1", 1), new ProxyCredentialsDTO("user_1", "111")),
                new ProxyConfigHolderDto(new ProxyNetworkConfigDTO("host_2", 2), new ProxyCredentialsDTO("user_2", "222"))
        );
        List<ProxyConfigHolderDto> result = reader.readData(path, ProxyConfigHolderDto.class);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testReadData_whenCantMap_shouldThrowDataParsingExceptionCausedByUnrecognizedPropertyException() {
        String path = "src/test/resources/testProxy.json";
        assertThatThrownBy(() -> reader.readData(path, ScenarioDto.class))
                .isInstanceOf(DataParsingException.class)
                .cause().isInstanceOf(UnrecognizedPropertyException.class);
    }

    @Test
    void testReadData_whenBadFile_shouldThrowDataParsingException() {
        String path = "src/test/resources/badProxy.json";
        assertThatThrownBy(() -> reader.readData(path, ProxyConfigHolderDto.class))
                .isInstanceOf(DataParsingException.class);
    }

    @Test
    void testReadData_whenFileNotFound_shouldThrowDataParsingExceptionCausedByFileNotfoundException() {
        String path = "src/test/resources/abracadabra.json";
        assertThatThrownBy(() -> reader.readData(path, ProxyConfigHolderDto.class))
                .isInstanceOf(DataParsingException.class)
                .cause().isInstanceOf(FileNotFoundException.class);
    }
}