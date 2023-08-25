package executor.service.publisher.source;

import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.model.ProxySource;
import executor.service.publisher.source.reader.FileReader;
import executor.service.publisher.source.service.proxy.ProxySourceService;
import executor.service.publisher.source.service.proxy.ProxySourceServiceFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProxySourceServiceFileTest {

    private FileReader reader;
    private ProxySourceService service;
    private ProxySource dto;
    private
    @BeforeEach
    void setUp() {
        reader = mock(FileReader.class);
        dto = new ProxySource("/some/path", "file", "http");
        service = new ProxySourceServiceFile(reader);
    }
    @Test
    void testLoadData() {
        List<ProxyConfigHolder> expected = List.of(new ProxyConfigHolder());
        when(reader.readData(anyString(), eq(ProxyConfigHolder.class))).thenReturn(expected);
        List<ProxyConfigHolder> result = service.loadData(dto);
        assertThat(result).isEqualTo(expected);
    }
}