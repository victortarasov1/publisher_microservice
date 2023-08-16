package executor.service.publisher.controller.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.publisher.model.ProxySourceDto;
import executor.service.publisher.processing.proxy.ProxyRemoteProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ProxyRemoteSourceControllerTest {
    public static final String BASE_URL = "/publisher/proxy/remote";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProxyRemoteProcessingService service;

    @Test
    void testLoadFromDefaultRemoteSource() throws Exception {
        mockMvc.perform(post(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    void testLoadFromCustomRemoteSource() throws Exception {
        ProxySourceDto dto = new ProxySourceDto();
        mockMvc.perform(post(BASE_URL + "/custom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

    }
}