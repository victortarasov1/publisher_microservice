package executor.service.model;


import java.util.List;
import jakarta.validation.constraints.NotEmpty;
public record Scenario(@NotEmpty String id, @NotEmpty String name, @NotEmpty String site, @NotEmpty List<Step> steps) {
}