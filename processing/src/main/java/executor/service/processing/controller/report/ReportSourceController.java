package executor.service.processing.controller.report;

import executor.service.model.ScenarioReport;
import executor.service.processing.controller.SourceController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/publisher/proxy")
public interface ReportSourceController extends SourceController<ScenarioReport> {

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    void add(@RequestBody ScenarioReport element);

    @PreAuthorize("isAuthenticated()")
    @PostMapping( "/all")
    void addAll(@RequestBody List<ScenarioReport> elements);

}
