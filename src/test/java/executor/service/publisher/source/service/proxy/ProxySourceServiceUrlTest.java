package executor.service.publisher.source.service.proxy;

import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.model.ProxySource;
import executor.service.publisher.source.okhttp.OkhttpLoader;
import okhttp3.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProxySourceServiceUrlTest {
    private OkhttpLoader loader;
    private ProxySourceService service;
    private ProxySource dto;

    @BeforeEach
    void setUp() {
        loader = mock(OkhttpLoader.class);
        dto = new ProxySource("http://some/url", "url", "http");
        service = new ProxySourceServiceUrl(loader);
    }

    @Test
    void testLoadData() {
        List<ProxyConfigHolder> expected = List.of();
        when(loader.loadData(any(Request.class), any())).thenReturn(List.of());
        List<ProxyConfigHolder> result = service.loadData(dto);
        assertThat(result).isEqualTo(expected);
    }

}