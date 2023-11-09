package executor.service.processing.controller.scenario;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.Scenario;
import executor.service.processing.service.scenario.ScenarioProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ScenarioSourceControllerTest {

    public static final String BASE_URL = "/publisher/scenario";
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ScenarioProcessingService service;
    @Test
    void testAdd() throws Exception {
        Scenario dto = new Scenario("some name", "some site", List.of());
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddAll() throws Exception {
        List<Scenario> dtoList = List.of(new Scenario("some name", "some site", List.of()));
        mockMvc.perform(post(BASE_URL + "/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoList)))
                .andExpect(status().isOk());
    }


}