package executor.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scenario {
    private UUID uuid;
    private String name;
    private String site;
    private List<Step> steps;

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
        steps.forEach(st -> st.setScenarioUUID(uuid));
    }

}