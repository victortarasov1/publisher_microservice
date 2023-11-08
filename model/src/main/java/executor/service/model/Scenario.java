package executor.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scenario {
    private UUID uuid;
    private String name;
    private String site;
    private List<Step> steps = new ArrayList<>();

    public Scenario(String name, String site, List<Step> steps) {
        this.name = name;
        this.site = site;
        this.steps = steps;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
        steps.forEach(st -> st.setScenarioUUID(uuid));
    }

}