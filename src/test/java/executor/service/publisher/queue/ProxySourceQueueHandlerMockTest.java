package executor.service.publisher.queue;

import executor.service.publisher.model.ProxyConfigHolderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProxySourceQueueHandlerMockTest {
    private QueueHandler<ProxyConfigHolderDto> queueHandler;
    @Mock
    private ConcurrentLinkedQueue<ProxyConfigHolderDto> proxies;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        proxies = mock(ConcurrentLinkedQueue.class);
        queueHandler = new ProxySourceQueueHandler<>();
        Field proxiesField = ProxySourceQueueHandler.class.getDeclaredField("proxies");
        proxiesField.setAccessible(true);
        proxiesField.set(queueHandler, proxies);
    }

    @Test
    public void testAdd() {
        ProxyConfigHolderDto element = new ProxyConfigHolderDto();
        queueHandler.add(element);
        verify(proxies, times(1)).add(element);
    }

    @Test
    public void testAddAll() {
        List<ProxyConfigHolderDto> elements = List.of(new ProxyConfigHolderDto(), new ProxyConfigHolderDto());
        queueHandler.addAll(elements);
        verify(proxies, times(1)).addAll(elements);
    }

    @Test
    public void testPoll() {
        ProxyConfigHolderDto expected = new ProxyConfigHolderDto();
        when(proxies.poll()).thenReturn(expected);
        Optional<ProxyConfigHolderDto> result = queueHandler.poll();
        assertThat(result).isPresent().contains(expected);
        verify(proxies, times(1)).poll();
    }

    @Test
    public void testRemoveAll() {
        ProxyConfigHolderDto proxy1 = new ProxyConfigHolderDto();
        ProxyConfigHolderDto proxy2 = new ProxyConfigHolderDto();
        List<ProxyConfigHolderDto> expected = List.of(proxy1, proxy2);
        when(proxies.poll()).thenReturn(proxy1, proxy2, null);
        List<ProxyConfigHolderDto> result = queueHandler.removeAll();
        assertThat(result).isEqualTo(expected);
        verify(proxies, times(3)).poll();
    }

}
