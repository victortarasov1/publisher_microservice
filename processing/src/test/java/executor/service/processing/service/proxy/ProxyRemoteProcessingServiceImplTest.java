package executor.service.processing.service.proxy;

import executor.service.model.ProxyConfigHolder;
import executor.service.queue.producer.proxy.ProxyQueueProducer;
import executor.service.source.exception.UnknownSourceServiceException;
import executor.service.source.model.ProxySource;
import executor.service.source.service.proxy.ProxySourceService;
import executor.service.validator.ProxyValidator;
import executor.service.validator.exception.UnknownProxyTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ProxyRemoteProcessingServiceImplTest {
    private ProxyQueueProducer producer;
    private ProxyValidator validator;
    private ProxySourceService proxySourceService;
    private ProxyRemoteProcessingService service;

    @BeforeEach
    void setUp() {
        producer = Mockito.mock(ProxyQueueProducer.class);
        proxySourceService = Mockito.mock(ProxySourceService.class);
        validator = mock(ProxyValidator.class);
        ProxySource defaultDto = new ProxySource("https://some/url", "url", "http");
        when(validator.getType()).thenReturn(defaultDto.getType());
        when(proxySourceService.getType()).thenReturn(defaultDto.getStorage());
        service = new ProxyRemoteProcessingServiceImpl(List.of(validator), List.of(proxySourceService), defaultDto, producer);
    }

    @Test
    void testLoadFromDefaultRemoteSource() {
        List<ProxyConfigHolder> proxies = List.of(new ProxyConfigHolder(), new ProxyConfigHolder(), new ProxyConfigHolder());
        when(validator.isValid(any(ProxyConfigHolder.class))).thenReturn(true);
        when(proxySourceService.loadData(any(ProxySource.class))).thenReturn(proxies);
        service.loadFromDefaultRemoteSource();
        verify(validator, timeout(2000).times(3)).isValid(any(ProxyConfigHolder.class));
        verify(proxySourceService, timeout(2000).times(1)).loadData(any(ProxySource.class));
        verify(producer, timeout(2000).times(3)).add(any(ProxyConfigHolder.class));
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
        verify(producer, timeout(2000).times(3)).add(any(ProxyConfigHolder.class));
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