package executor.service.processing.controller.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.processing.service.proxy.ProxyProcessingService;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ProxySourceControllerTest {

    public static final String BASE_URL = "/publisher/proxy";
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ProxyProcessingService service;

    @Test
    void testAdd() throws Exception {
        ProxyConfigHolder dto = new ProxyConfigHolder();
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddAll() throws Exception {
        List<ProxyConfigHolder> dtoList = List.of(new ProxyConfigHolder());
        mockMvc.perform(post(BASE_URL + "/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoList)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testPoll() throws Exception {
        ProxyConfigHolder dto = new ProxyConfigHolder(new ProxyNetworkConfig("host", 1), new ProxyCredentials("username", "password"));
        when(service.poll()).thenReturn(Optional.of(dto));
        mockMvc.perform(delete(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("proxyNetworkConfig.hostname").value(dto.getProxyNetworkConfig().getHostname()))
                .andExpect(jsonPath("proxyNetworkConfig.port").value(dto.getProxyNetworkConfig().getPort()))
                .andExpect(jsonPath("proxyCredentials.username").value(dto.getProxyCredentials().getUsername()))
                .andExpect(jsonPath("proxyCredentials.password").value(dto.getProxyCredentials().getPassword()));
    }

    @Test
    @WithMockUser
    void testRemoveAll() throws Exception {
        List<ProxyConfigHolder> dtoList = List.of(new ProxyConfigHolder(), new ProxyConfigHolder());
        when(service.removeAll()).thenReturn(dtoList);
        mockMvc.perform(delete(BASE_URL + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser
    void testRemoveByCount() throws Exception {
        List<ProxyConfigHolder> dtoList = List.of(new ProxyConfigHolder(), new ProxyConfigHolder());
        when(service.removeByCount(anyInt())).thenReturn(dtoList);
        mockMvc.perform(delete(BASE_URL + "/count/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }

}
