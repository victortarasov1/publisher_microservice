package executor.service.publisher.source;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.source.reader.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

class ProxySourceServiceFileTest {

    private FileReader reader;
    private QueueHandler<ProxyConfigHolderDto> handler;
    private SourceService service;
    @BeforeEach
    void setUp() {
        reader = mock(FileReader.class);
        handler = mock(QueueHandler.class);
        service = new ProxySourceServiceFile(reader, handler, "/some/path");
    }
    @Test
    void testLoadData() {
        service.loadData();
        verify(handler, times(1)).addAll(anyList());
        verify(reader, times(1)).readData(anyString(), eq(ProxyConfigHolderDto.class));
    }
}