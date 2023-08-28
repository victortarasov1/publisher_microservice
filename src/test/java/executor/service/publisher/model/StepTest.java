package executor.service.publisher.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StepTest {

    private static final String TEST_ACTION = "testAction";
    private static final String TEST_VALUE = "testValue";

    @Test
    public void testDefaultConstructor() {
        Step step = new Step();
        assertThat(step.getAction()).isNull();
        assertThat(step.getValue()).isNull();
    }

    @Test
    public void testParameterizedConstructor() {
        Step step = new Step(TEST_ACTION, TEST_VALUE);

        assertThat(step.getAction()).isEqualTo(TEST_ACTION);
        assertThat(step.getValue()).isEqualTo(TEST_VALUE);
    }

    @Test
    public void testActionGetterAndSetter() {
        Step step = new Step();
        step.setAction(TEST_ACTION);
        assertThat(step.getAction()).isEqualTo(TEST_ACTION);
    }

    @Test
    public void testValueGetterAndSetter() {
        Step step = new Step();
        step.setValue(TEST_VALUE);
        assertThat(step.getValue()).isEqualTo(TEST_VALUE);
    }

    @Test
    public void testEqualsWithNull() {
        Step step1 = new Step(TEST_ACTION, TEST_VALUE);
        assertThat(step1).isNotEqualTo(null);
    }

    @Test
    public void testEqualsWithDifferentType() {
        Step step1 = new Step(TEST_ACTION, TEST_VALUE);
        String differentType = "string";
        assertThat(step1).isNotEqualTo(differentType);
    }

    @Test
    public void testEqualsWithItself() {
        Step step1 = new Step(TEST_ACTION, TEST_VALUE);
        assertThat(step1).isEqualTo(step1);
    }

    @Test
    public void testEqualsAndHashCodeForEqualSteps() {
        Step step1 = new Step(TEST_ACTION, TEST_VALUE);
        Step step2 = new Step(TEST_ACTION, TEST_VALUE);
        assertThat(step1).isEqualTo(step2);
        assertThat(step1.hashCode()).isEqualTo(step2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeForDifferentSteps() {
        Step step1 = new Step(TEST_ACTION, TEST_VALUE);
        Step step3 = new Step("differentAction", "differentValue");
        assertThat(step1).isNotEqualTo(step3);
        assertThat(step1.hashCode()).isNotEqualTo(step3.hashCode());
    }

    @Test
    public void testToString() {
        Step step = new Step(TEST_ACTION, TEST_VALUE);
        String expectedToString = "StepDto{action='" + TEST_ACTION + "', value='" + TEST_VALUE + "'}";
        assertThat(step.toString()).isEqualTo(expectedToString);
    }
}
