package executor.service.publisher.controllers;

import executor.service.publisher.controller.proxysource.ProxySourceControllerImpl;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProxySourceControllerTest {

    @Mock
    private QueueHandler<ProxyConfigHolderDto> mockProxyHandler;
    private ProxySourceControllerImpl proxySourceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        proxySourceController = new ProxySourceControllerImpl(mockProxyHandler);
    }

    @Test
    public void testAddProxyConfigHolderDto() {
        ProxyConfigHolderDto proxyConfigHolderDto = new ProxyConfigHolderDto();
        ResponseEntity<?> responseEntity = proxySourceController.add(proxyConfigHolderDto);

        verify(mockProxyHandler, times(1)).add(proxyConfigHolderDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testAddAllProxyConfigHolderDtos() {
        List<ProxyConfigHolderDto> proxyConfigHolderDtos = Collections.singletonList(new ProxyConfigHolderDto());
        ResponseEntity<?> responseEntity = proxySourceController.addAll(proxyConfigHolderDtos);

        verify(mockProxyHandler, times(1)).addAll(proxyConfigHolderDtos);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testPoll() {
        ProxyConfigHolderDto expectedProxy = new ProxyConfigHolderDto();
        when(mockProxyHandler.poll()).thenReturn(Optional.of(expectedProxy));
        ResponseEntity<?> responseEntity = proxySourceController.poll();

        verify(mockProxyHandler, times(1)).poll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Optional<ProxyConfigHolderDto> responseProxyOptional = (Optional<ProxyConfigHolderDto>) responseEntity.getBody();
        ProxyConfigHolderDto responseProxy = responseProxyOptional.orElse(null);

        assertEquals(expectedProxy, responseProxy);
    }

    @Test
    public void testRemoveAll() {
        List<ProxyConfigHolderDto> removedProxies = Collections.singletonList(new ProxyConfigHolderDto());
        when(mockProxyHandler.removeAll()).thenReturn(removedProxies);

        ResponseEntity<?> responseEntity = proxySourceController.removeAll();
        verify(mockProxyHandler, times(1)).removeAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(removedProxies, responseEntity.getBody());
    }
}
