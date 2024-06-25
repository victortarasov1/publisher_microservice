package executor.service.controller;

import executor.service.model.Scenario;
import executor.service.processing.ScenarioProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scenario")
public class ScenarioSourceController {
    private final ScenarioProcessor processor;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Scenario scenario) {
        processor.add(scenario);

        System.out.println();
    }

    @DeleteMapping
    public void delete(@RequestParam String scenarioId) {
        processor.delete(scenarioId);
    }


    @PatchMapping
    public void update(@RequestBody Scenario scenario) {
        processor.update(scenario);
    }
    @PostMapping
    public void execute(@RequestParam String scenarioId) {
        processor.execute(scenarioId);
    }

    @GetMapping
    public Scenario get(@RequestParam String scenarioId) {
        return processor.get(scenarioId);
    }

    @GetMapping("/all")
    public List<Scenario> getAll() {
        return processor.getAll();
    }
}
