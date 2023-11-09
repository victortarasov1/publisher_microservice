package executor.service.model;


import java.util.List;

public record Scenario(String name, String site, List<Step> steps) {
}