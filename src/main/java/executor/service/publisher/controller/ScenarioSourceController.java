package executor.service.publisher.controller;

import executor.service.publisher.model.ScenarioDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publisher")
public interface ScenarioSourceController {
    @PostMapping(value = "scenario", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ScenarioDto> add(@RequestBody ScenarioDto scenario);

    @PostMapping(value = "scenarios", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ScenarioDto> addAll(@RequestBody List<ScenarioDto> scenarios);

    @DeleteMapping(value = "scenario", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Optional<ScenarioDto>> poll();

    @DeleteMapping(value = "scenarios", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ScenarioDto>> removeAll();

}
