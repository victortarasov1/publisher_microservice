package executor.service.processing.proxy;

import executor.service.exception.source.UnknownSourceServiceException;
import executor.service.exception.validator.UnknownProxyTypeException;
import executor.service.model.ProxySource;
import executor.service.model.ProxyConfigHolder;
import executor.service.collection.queue.proxy.ProxySourceQueueHandler;
import executor.service.source.service.proxy.ProxySourceService;
import executor.service.validation.ProxyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ProxyRemoteProcessingServiceImplTest {
    private ProxySourceQueueHandler queueHandler;
    private ProxyValidator validator;
    private ProxySourceService proxySourceService;
    private ProxyRemoteProcessingService service;

    @BeforeEach
    void setUp() {
        queueHandler = mock(ProxySourceQueueHandler.class);
        proxySourceService = mock(ProxySourceService.class);
        validator = mock(ProxyValidator.class);
        ProxySource defaultDto = new ProxySource("https://some/url", "url", "http");
        when(validator.getType()).thenReturn(defaultDto.getType());
        when(proxySourceService.getType()).thenReturn(defaultDto.getStorage());
        service = new ProxyRemoteProcessingServiceImpl(List.of(validator), List.of(proxySourceService), defaultDto, queueHandler);
    }

    @Test
    void testLoadFromDefaultRemoteSource() {
        List<ProxyConfigHolder> proxies = List.of(new ProxyConfigHolder(), new ProxyConfigHolder(), new ProxyConfigHolder());
        when(validator.isValid(any(ProxyConfigHolder.class))).thenReturn(true);
        when(proxySourceService.loadData(any(ProxySource.class))).thenReturn(proxies);
        service.loadFromDefaultRemoteSource();
        verify(validator, timeout(2000).times(3)).isValid(any(ProxyConfigHolder.class));
        verify(proxySourceService, timeout(2000).times(1)).loadData(any(ProxySource.class));
        verify(queueHandler, timeout(2000).times(3)).add(any(ProxyConfigHolder.class));
    }

    @Test
    void testLoadFromCustomRemoteSource() {
        ProxySource customSource = new ProxySource("https://some/other/url", "url", "http");
        List<ProxyConfigHolder> proxies = List.of(new ProxyConfigHolder(), new ProxyConfigHolder(), new ProxyConfigHolder());
        when(validator.isValid(any(ProxyConfigHolder.class))).thenReturn(true);
        when(proxySourceService.loadData(any(ProxySource.class))).thenReturn(proxies);
        service.loadFromCustomRemoteSource(customSource);
        verify(validator, timeout(2000).times(3)).isValid(any(ProxyConfigHolder.class));
        verify(proxySourceService, timeout(2000).times(1)).loadData(any(ProxySource.class));
        verify(queueHandler, timeout(2000).times(3)).add(any(ProxyConfigHolder.class));
    }

    @Test
    void testLoadFromCustomRemoteSource_shouldThrowUnknownProxyTypeException() {
        ProxySource customSource = new ProxySource("https://some/other/url", "url", "");
        assertThatThrownBy(() -> service.loadFromCustomRemoteSource(customSource)).isInstanceOf(UnknownProxyTypeException.class);
    }

    @Test
    void testLoadFromCustomRemoteSource_shouldThrowUnknownServiceException() {
        ProxySource customSource = new ProxySource("https://some/other/url", "", "http");
        assertThatThrownBy(() -> service.loadFromCustomRemoteSource(customSource)).isInstanceOf(UnknownSourceServiceException.class);
    }
}