package executor.service.publisher.controller.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxyCredentialsDTO;
import executor.service.publisher.model.ProxyNetworkConfigDTO;
import executor.service.publisher.processing.ProcessingService;
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

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProcessingService<ProxyConfigHolderDto> service;

    @Test
    void testAdd() throws Exception {
        ProxyConfigHolderDto dto = new ProxyConfigHolderDto();
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddAll() throws Exception {
        List<ProxyConfigHolderDto> dtoList = List.of(new ProxyConfigHolderDto());
        mockMvc.perform(post(BASE_URL + "/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoList)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testPoll() throws Exception {
        ProxyConfigHolderDto dto = new ProxyConfigHolderDto(new ProxyNetworkConfigDTO("host", 1), new ProxyCredentialsDTO("username", "password"));
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
        List<ProxyConfigHolderDto> dtoList = List.of(new ProxyConfigHolderDto(), new ProxyConfigHolderDto());
        when(service.removeAll()).thenReturn(dtoList);
        mockMvc.perform(delete(BASE_URL + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser
    void testRemoveByCount() throws Exception {
        List<ProxyConfigHolderDto> dtoList = List.of(new ProxyConfigHolderDto(), new ProxyConfigHolderDto());
        when(service.removeByCount(anyInt())).thenReturn(dtoList);
        mockMvc.perform(delete(BASE_URL + "/count/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }

}
