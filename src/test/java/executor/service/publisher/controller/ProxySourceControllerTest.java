package executor.service.publisher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxyCredentialsDTO;
import executor.service.publisher.model.ProxyNetworkConfigDTO;
import executor.service.publisher.queue.QueueHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ProxySourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueueHandler<ProxyConfigHolderDto> handler;
    @InjectMocks
    private ProxySourceControllerImpl proxySourceController;

    @BeforeEach
    public void setup() {
        proxySourceController = new ProxySourceControllerImpl(handler);
    }

    private final ProxyConfigHolderDto testProxyConfig = new ProxyConfigHolderDto(
            new ProxyNetworkConfigDTO("proxy.example.com", 8080),
            new ProxyCredentialsDTO("username", "password")
    );

    private final ProxyConfigHolderDto testProxyConfig2 = new ProxyConfigHolderDto(
            new ProxyNetworkConfigDTO("proxy2.example.com", 8081),
            new ProxyCredentialsDTO("user2", "pass2")
    );

    private final List<ProxyConfigHolderDto> testProxies = List.of(testProxyConfig, testProxyConfig2);

    @Test
    public void shouldAddElementToQueue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        this.mockMvc.perform(post("/publisher/proxy").contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(testProxyConfig))).andExpect(status().isOk());
        verify(handler, times(1)).add(testProxyConfig);
    }

    @Test
    public void shouldAddAllElementsToQueue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        this.mockMvc.perform(post("/publisher/proxies").contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(testProxies))).andExpect(status().isOk());
        verify(handler, times(1)).addAll(testProxies);
    }

    @Test
    void shouldReturnElementFromQueue() throws Exception {
        when(handler.poll()).thenReturn(Optional.of(testProxyConfig));
        this.mockMvc.perform(delete("/publisher/proxy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.proxyNetworkConfig.hostname").value("proxy.example.com"))
                .andExpect(jsonPath("$.proxyNetworkConfig.port").value(8080))
                .andExpect(jsonPath("$.proxyCredentials.username").value("username"))
                .andExpect(jsonPath("$.proxyCredentials.password").value("password"));
    }

    @Test
    void shouldReturnAllElementsFromQueue() throws Exception {
        when(handler.removeAll()).thenReturn(testProxies);
        this.mockMvc.perform(delete("/publisher/proxies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].proxyNetworkConfig.hostname").value("proxy.example.com"))
                .andExpect(jsonPath("$[0].proxyNetworkConfig.port").value(8080))
                .andExpect(jsonPath("$[0].proxyCredentials.username").value("username"))
                .andExpect(jsonPath("$[0].proxyCredentials.password").value("password"))
                .andExpect(jsonPath("$[1].proxyNetworkConfig.hostname").value("proxy2.example.com"))
                .andExpect(jsonPath("$[1].proxyNetworkConfig.port").value(8081))
                .andExpect(jsonPath("$[1].proxyCredentials.username").value("user2"))
                .andExpect(jsonPath("$[1].proxyCredentials.password").value("pass2"));
    }

    @Test
    public void testAddProxyConfigHolderDto() {
        ProxyConfigHolderDto proxyConfigHolderDto = new ProxyConfigHolderDto();
        ResponseEntity<?> responseEntity = proxySourceController.add(proxyConfigHolderDto);

        verify(handler, times(1)).add(proxyConfigHolderDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testAddAllProxyConfigHolderDtos() {
        List<ProxyConfigHolderDto> proxyConfigHolderDtos = Collections.singletonList(new ProxyConfigHolderDto());
        ResponseEntity<?> responseEntity = proxySourceController.addAll(proxyConfigHolderDtos);

        verify(handler, times(1)).addAll(proxyConfigHolderDtos);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testPoll() {
        ProxyConfigHolderDto expectedProxy = new ProxyConfigHolderDto();
        when(handler.poll()).thenReturn(Optional.of(expectedProxy));
        ResponseEntity<Optional<ProxyConfigHolderDto>> responseEntity = proxySourceController.poll();

        verify(handler, times(1)).poll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Optional<ProxyConfigHolderDto> responseProxyOptional = responseEntity.getBody();
        assertEquals(Optional.of(expectedProxy), responseProxyOptional);
    }

    @Test
    public void testRemoveAll() {
        List<ProxyConfigHolderDto> removedProxies = Collections.singletonList(new ProxyConfigHolderDto());
        when(handler.removeAll()).thenReturn(removedProxies);

        ResponseEntity<?> responseEntity = proxySourceController.removeAll();
        verify(handler, times(1)).removeAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(removedProxies, responseEntity.getBody());
    }
}
