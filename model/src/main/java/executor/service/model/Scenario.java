package executor.service.model;


import java.util.List;

public record Scenario(String id, String name, String site, List<Step> steps) {
}