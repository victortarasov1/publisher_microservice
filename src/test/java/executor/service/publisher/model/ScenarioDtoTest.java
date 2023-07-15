package executor.service.publisher.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ScenarioDtoTest {
    private static final String name = "name";
    private static final String actualName = "name2";
    private static final String site = "site";
    private static final String actualSite = "site2";
    private static final ArrayList<StepDto> stepList = new ArrayList<>();
    private ScenarioDto scenarioDto;
    private ScenarioDto actualDto;
    private ScenarioDto expectedDto;

    @BeforeEach
    public void initEach() {
        scenarioDto = new ScenarioDto(name, site, stepList);
        actualDto = new ScenarioDto(actualName, actualSite, stepList);
        expectedDto = new ScenarioDto(name, site, stepList);
    }

    @Test
    public void testEqualsAndHashCode() {
        assertEquals(scenarioDto, expectedDto);
        assertEquals(scenarioDto.hashCode(), expectedDto.hashCode());
    }

    @Test
    public void settersTest() {
        scenarioDto.setName(name);
        scenarioDto.setSite(site);
        scenarioDto.setSteps(stepList);
        assertEquals(scenarioDto, expectedDto);
    }

    @Test
    public void gettersTest() {
        scenarioDto.setName(actualDto.getName());
        scenarioDto.setSite(actualDto.getSite());
        scenarioDto.setSteps(actualDto.getSteps());
        Assertions.assertEquals(actualDto, scenarioDto);
    }

}