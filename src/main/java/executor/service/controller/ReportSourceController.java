package executor.service.controller;

import executor.service.model.ScenarioReport;
import executor.service.processing.ReportProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportSourceController {
    private final ReportProcessor processor;
    @GetMapping
    public List<ScenarioReport> get(@RequestParam String scenarioId) {
        return processor.findByScenarioId(scenarioId);
    }

    @GetMapping("/all")
    public List<ScenarioReport> findAll() {
        return processor.findAll();
    }


}
