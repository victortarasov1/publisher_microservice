package executor.service.publisher.processing.proxy;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxySourceDto;
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
        ProxySourceDto defaultDto = new ProxySourceDto("https://some/url", "url", "http");
        when(validator.getType()).thenReturn(defaultDto.getType());
        service = new ProxyProcessingServiceImpl(List.of(validator), queueHandler, defaultDto);
    }

    @Test
    void testAdd() {
        ProxyConfigHolderDto proxy = new ProxyConfigHolderDto();
        when(validator.isValid(any(ProxyConfigHolderDto.class))).thenReturn(true);
        service.add(proxy);
        verify(queueHandler, timeout(2000).times(1)).add(any(ProxyConfigHolderDto.class));
    }

    @Test
    void testAddAll() {
        List<ProxyConfigHolderDto> proxies = List.of(new ProxyConfigHolderDto(), new ProxyConfigHolderDto());
        when(validator.isValid(any(ProxyConfigHolderDto.class))).thenReturn(true);
        service.addAll(proxies);
        verify(queueHandler, timeout(2000).times(2)).add(any(ProxyConfigHolderDto.class));
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
        ProxyConfigHolderDto proxy = new ProxyConfigHolderDto();
        when(queueHandler.poll()).thenReturn(Optional.of(proxy));
        Optional<ProxyConfigHolderDto> polledDto = service.poll();
        assertThat(polledDto).isEqualTo(Optional.of(proxy));
    }

}