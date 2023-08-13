package executor.service.publisher.controller.scenario;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.publisher.model.*;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ScenarioSourceControllerTest {

    public static final String BASE_URL = "/publisher/scenario";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProcessingService<ScenarioDto> service;
    @Test
    void testAdd() throws Exception {
        ScenarioDto dto = new ScenarioDto();
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddAll() throws Exception {
        List<ScenarioDto> dtoList = List.of(new ScenarioDto());
        mockMvc.perform(post(BASE_URL + "/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoList)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testPoll() throws Exception {
        ScenarioDto dto = new ScenarioDto("some name", "some site`s url", List.of());
        when(service.poll()).thenReturn(Optional.of(dto));
        mockMvc.perform(delete(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(dto.getName()))
                .andExpect(jsonPath("site").value(dto.getSite()))
                .andExpect(jsonPath("steps", hasSize(0)));

    }

    @Test
    @WithMockUser
    void testRemoveAll() throws Exception {
        List<ScenarioDto> dtoList = List.of(new ScenarioDto(), new ScenarioDto());
        when(service.removeAll()).thenReturn(dtoList);
        mockMvc.perform(delete(BASE_URL + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser
    void testRemoveByCount() throws Exception {
        List<ScenarioDto> dtoList = List.of(new ScenarioDto(), new ScenarioDto());
        when(service.removeByCount(anyInt())).thenReturn(dtoList);
        mockMvc.perform(delete(BASE_URL + "/count/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }
}