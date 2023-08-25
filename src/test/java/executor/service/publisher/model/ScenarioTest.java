package executor.service.publisher.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ScenarioTest {
    private static final String name = "name";
    private static final String actualName = "name2";
    private static final String site = "site";
    private static final String actualSite = "site2";
    private static final ArrayList<Step> stepList = new ArrayList<>();
    private Scenario scenario;
    private Scenario actualDto;
    private Scenario expectedDto;

    @BeforeEach
    public void initEach() {
        scenario = new Scenario(name, site, stepList);
        actualDto = new Scenario(actualName, actualSite, stepList);
        expectedDto = new Scenario(name, site, stepList);
    }

    @Test
    public void testEqualsAndHashCode() {
        assertEquals(scenario, expectedDto);
        assertEquals(scenario.hashCode(), expectedDto.hashCode());
    }

    @Test
    public void settersTest() {
        scenario.setName(name);
        scenario.setSite(site);
        scenario.setSteps(stepList);
        assertEquals(scenario, expectedDto);
    }

    @Test
    public void gettersTest() {
        scenario.setName(actualDto.getName());
        scenario.setSite(actualDto.getSite());
        scenario.setSteps(actualDto.getSteps());
        Assertions.assertEquals(actualDto, scenario);
    }

}