package executor.service.processing.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.source.model.ProxySource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class RestExceptionHandlerTest {
    private static final String LOAD_FROM_CUSTOM_SOURCE_URL = "/publisher/proxy/remote/custom";
    private static final String REMOVE_BY_COUNT_URL =  "/publisher/proxy/count/{size}";
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testLoadFromCustomRemoteSource_shouldHandleUnknownSourceServiceTypeException() throws Exception {
        ProxySource proxySource = new ProxySource("/some/url", "ddd", "http");
        mockMvc.perform(post(LOAD_FROM_CUSTOM_SOURCE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(proxySource)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("unknown type of of source service: ddd"))
                .andExpect(jsonPath("$.debugMessage").isEmpty());
    }

    @Test
    public void testLoadFromCustomRemoteSource_shouldHandleUnknownProxyTypeException() throws Exception {
        ProxySource proxySource = new ProxySource("/some/url", "url", "ddd");
        mockMvc.perform(post(LOAD_FROM_CUSTOM_SOURCE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(proxySource)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("unknown proxy type exception: ddd"))
                .andExpect(jsonPath("$.debugMessage").isEmpty());
    }

    @Test
    void testLoadFromCustomRemoteSource_shouldHandleHttpMessageNotReadable() throws Exception {
        mockMvc.perform(post(LOAD_FROM_CUSTOM_SOURCE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Malformed JSON Request"));
    }
}