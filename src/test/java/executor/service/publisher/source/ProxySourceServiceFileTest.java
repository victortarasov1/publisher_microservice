package executor.service.publisher.source;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxySourceDto;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.source.reader.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.lang.reflect.Proxy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProxySourceServiceFileTest {

    private FileReader reader;
    private SourceService<ProxyConfigHolderDto, ProxySourceDto> service;
    private ProxySourceDto dto;
    private
    @BeforeEach
    void setUp() {
        reader = mock(FileReader.class);
        dto = new ProxySourceDto("/some/path", "file", "http");
        service = new ProxySourceServiceFile(reader);
    }
    @Test
    void testLoadData() {
        List<ProxyConfigHolderDto> expected = List.of(new ProxyConfigHolderDto());
        when(reader.readData(anyString(), eq(ProxyConfigHolderDto.class))).thenReturn(expected);
        List<ProxyConfigHolderDto> result = service.loadData(dto);
        assertThat(result).isEqualTo(expected);
    }
}