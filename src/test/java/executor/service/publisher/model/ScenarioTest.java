package executor.service.publisher.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class ScenarioTest {

    private static final String TEST_NAME = "testName";
    private static final String TEST_SITE = "testSite";
    private static final List<Step> TEST_STEPS = new ArrayList<>();
    private static final String DIFFERENT_NAME = "differentName";
    private static final String DIFFERENT_SITE = "differentSite";

    @Test
    public void testDefaultConstructor() {
        Scenario scenario = new Scenario();
        assertThat(scenario.getName()).isNull();
        assertThat(scenario.getSite()).isNull();
        assertThat(scenario.getSteps()).isNull();
    }

    @Test
    public void testParameterizedConstructor() {
        Scenario scenario = new Scenario(TEST_NAME, TEST_SITE, TEST_STEPS);

        assertThat(scenario.getName()).isEqualTo(TEST_NAME);
        assertThat(scenario.getSite()).isEqualTo(TEST_SITE);
        assertThat(scenario.getSteps()).isEqualTo(TEST_STEPS);
    }

    @Test
    public void testNameGetterAndSetter() {
        Scenario scenario = new Scenario();
        scenario.setName(TEST_NAME);
        assertThat(scenario.getName()).isEqualTo(TEST_NAME);
    }

    @Test
    public void testSiteGetterAndSetter() {
        Scenario scenario = new Scenario();
        scenario.setSite(TEST_SITE);
        assertThat(scenario.getSite()).isEqualTo(TEST_SITE);
    }

    @Test
    public void testStepsGetterAndSetter() {
        Scenario scenario = new Scenario();
        scenario.setSteps(TEST_STEPS);
        assertThat(scenario.getSteps()).isEqualTo(TEST_STEPS);
    }

    @Test
    public void testEqualsWithNull() {
        Scenario scenario1 = new Scenario(TEST_NAME, TEST_SITE, TEST_STEPS);
        assertThat(scenario1).isNotEqualTo(null);
    }

    @Test
    public void testEqualsWithDifferentType() {
        Scenario scenario1 = new Scenario(TEST_NAME, TEST_SITE, TEST_STEPS);
        String differentType = "string";
        assertThat(scenario1).isNotEqualTo(differentType);
    }

    @Test
    public void testEqualsWithItself() {
        Scenario scenario1 = new Scenario(TEST_NAME, TEST_SITE, TEST_STEPS);
        assertThat(scenario1).isEqualTo(scenario1);
    }

    @Test
    public void testEqualsAndHashCodeForEqualScenarios() {
        Scenario scenario1 = new Scenario(TEST_NAME, TEST_SITE, TEST_STEPS);
        Scenario scenario2 = new Scenario(TEST_NAME, TEST_SITE, TEST_STEPS);

        assertThat(scenario1).isEqualTo(scenario2);
        assertThat(scenario1.hashCode()).isEqualTo(scenario2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeForDifferentScenarios() {
        Scenario scenario1 = new Scenario(TEST_NAME, TEST_SITE, TEST_STEPS);
        Scenario scenario3 = new Scenario(DIFFERENT_NAME, DIFFERENT_SITE, new ArrayList<>());

        assertThat(scenario1).isNotEqualTo(scenario3);
        assertThat(scenario1.hashCode()).isNotEqualTo(scenario3.hashCode());
    }

    @Test
    public void testToString() {
        Scenario scenario = new Scenario(TEST_NAME, TEST_SITE, TEST_STEPS);
        String expectedToString = "Scenario{name='" + TEST_NAME + "', site='" + TEST_SITE + "', steps=" + TEST_STEPS + "}";
        assertThat(scenario.toString()).isEqualTo(expectedToString);
    }
}