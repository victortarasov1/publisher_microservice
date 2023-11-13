package executor.service.processing.service.proxy;

import executor.service.model.ProxyConfigHolder;
import executor.service.queue.producer.proxy.ProxyQueueProducer;
import executor.service.source.model.ProxySource;
import executor.service.validator.ProxyValidator;
import executor.service.validator.exception.UnknownProxyTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ProxyProcessingServiceImplTest {
    private static final String PROXY_TYPE = "http";
    private static final String BAD_PROXY_TYPE = "";
    private ProxyQueueProducer producer;
    private ProxyValidator validator;
    private ProxyProcessingService service;
    private ProxySource defaultSource;

    @BeforeEach
    void setUp() {
        producer = Mockito.mock(ProxyQueueProducer.class);
        validator = mock(ProxyValidator.class);
        defaultSource = Mockito.mock(ProxySource.class);
        when(validator.getType()).thenReturn(PROXY_TYPE);
        service = new ProxyProcessingServiceImpl(List.of(validator), producer, defaultSource);
    }

    @Test
    void testAdd() {
        ProxyConfigHolder proxy = new ProxyConfigHolder();
        when(defaultSource.getType()).thenReturn(PROXY_TYPE);
        when(validator.isValid(any(ProxyConfigHolder.class))).thenReturn(true);
        service.add(proxy);
        verify(producer, timeout(2000).times(1)).add(any(ProxyConfigHolder.class));
    }

    @Test
    void testAdd_shouldThrowUnknownProxyTypeException() {
        ProxyConfigHolder proxy = new ProxyConfigHolder();
        when(defaultSource.getType()).thenReturn(BAD_PROXY_TYPE);
        assertThatThrownBy(() -> service.add(proxy)).isInstanceOf(UnknownProxyTypeException.class);
    }

    @Test
    void testAddAll() {
        List<ProxyConfigHolder> proxies = List.of(new ProxyConfigHolder(), new ProxyConfigHolder());
        when(defaultSource.getType()).thenReturn(PROXY_TYPE);
        when(validator.isValid(any(ProxyConfigHolder.class))).thenReturn(true);
        service.addAll(proxies);
        verify(producer, timeout(2000).times(2)).add(any(ProxyConfigHolder.class));
    }
}