package executor.service.publisher.source;

import executor.service.publisher.exception.source.SourceException;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.source.okhttp.OkhttpLoader;
import okhttp3.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProxySourceServiceUrlTest {
    private OkhttpLoader loader;
    private QueueHandler<ProxyConfigHolderDto> handler;
    private SourceService service;

    @BeforeEach
    void setUp() {
        loader = mock(OkhttpLoader.class);
        handler = mock(QueueHandler.class);
        String url = "https://some/url";
        service = new ProxySourceServiceUrl(url, loader, handler);
    }

    @Test
    void testLoadData() {
        service.loadData();
        verify(handler, times(1)).addAll(anyList());
        verify(loader,times(1)).loadData(any(Request.class), any());
    }

    @Test
    void testLoadData_whenSourceExceptionShouldHandle() {
        when(loader.loadData(any(Request.class), any())).thenThrow(SourceException.class);
        assertDoesNotThrow(() -> service.loadData());
    }
}