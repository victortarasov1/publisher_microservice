package executor.service.publisher.processing.proxy;

import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.model.ProxySource;
import executor.service.publisher.queue.proxy.ProxySourceQueueHandler;
import executor.service.publisher.validation.ProxyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class ProxyProcessingServiceImplTest {
    private ProxySourceQueueHandler queueHandler;
    private ProxyValidator validator;
    private ProxyProcessingService service;

    @BeforeEach
    void setUp() {
        queueHandler = mock(ProxySourceQueueHandler.class);
        validator = mock(ProxyValidator.class);
        ProxySource defaultDto = new ProxySource("https://some/url", "url", "http");
        when(validator.getType()).thenReturn(defaultDto.getType());
        service = new ProxyProcessingServiceImpl(List.of(validator), queueHandler, defaultDto);
    }

    @Test
    void testAdd() {
        ProxyConfigHolder proxy = new ProxyConfigHolder();
        when(validator.isValid(any(ProxyConfigHolder.class))).thenReturn(true);
        service.add(proxy);
        verify(queueHandler, timeout(2000).times(1)).add(any(ProxyConfigHolder.class));
    }

    @Test
    void testAddAll() {
        List<ProxyConfigHolder> proxies = List.of(new ProxyConfigHolder(), new ProxyConfigHolder());
        when(validator.isValid(any(ProxyConfigHolder.class))).thenReturn(true);
        service.addAll(proxies);
        verify(queueHandler, timeout(2000).times(2)).add(any(ProxyConfigHolder.class));
    }

    @Test
    void testRemoveByCount() {
        int count = 5;
        service.removeByCount(count);
        verify(queueHandler, times(1)).removeByCount(count);
    }


    @Test
    void testRemoveAll() {
        service.removeAll();
        verify(queueHandler, times(1)).removeAll();
    }

    @Test
    void testPoll() {
        ProxyConfigHolder proxy = new ProxyConfigHolder();
        when(queueHandler.poll()).thenReturn(Optional.of(proxy));
        Optional<ProxyConfigHolder> polledDto = service.poll();
        assertThat(polledDto).isEqualTo(Optional.of(proxy));
    }

}