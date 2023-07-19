package executor.service.publisher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.model.StepDto;
import executor.service.publisher.queue.QueueHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {ScenarioSourceControllerImpl.class})
class ScenarioSourceControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueueHandler<ScenarioDto> handler;
    private final StepDto testStep = new StepDto("Test action 1", "Test value 1");
    private final List<StepDto> testSteps = List.of(testStep);
    private final ScenarioDto testScenario = new ScenarioDto("Test name 1", "Test site 1", testSteps);
    private final ScenarioDto testScenario2 = new ScenarioDto("Test name 2", "Test site 2", testSteps);
    private final List<ScenarioDto> testScenarios = List.of(testScenario, testScenario2);

    @Test
    public void shouldAddElementToQueue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        this.mockMvc.perform(post("/publisher/scenario").contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(testScenario))).andExpect(status().isOk());
        verify(handler, times(1)).add(testScenario);
    }

    @Test
    public void shouldAddAllElementsToQueue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        this.mockMvc.perform(post("/publisher/scenarios").contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(testScenarios))).andExpect(status().isOk());
        verify(handler, times(1)).addAll(testScenarios);
    }

    @Test
    void shouldReturnElementFromQueue() throws Exception {
        when(handler.poll()).thenReturn(Optional.of(testScenario));
        this.mockMvc.perform(delete("/publisher/scenario")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test name 1"));
    }

    @Test
    void shouldReturnAllElementsFromQueue() throws Exception {
        when(handler.removeAll()).thenReturn(testScenarios);
        this.mockMvc.perform(delete("/publisher/scenarios")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test name 1"))
                .andExpect(jsonPath("$[1].name").value("Test name 2"));
    }

}