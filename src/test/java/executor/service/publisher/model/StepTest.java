package executor.service.publisher.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StepTest {

    private Step stepDto;

    @BeforeEach
    public void setUp(){
        stepDto = new Step("Test action", "Test value");
    }

    @Test
    public void testEmptyConstructor(){
        Step actualStep = new Step();
        Step expectedStepDto = new Step();
        Assertions.assertEquals(expectedStepDto, actualStep);
    }

    @Test
    public void testSetters(){
        stepDto.setAction("Test action 2");
        stepDto.setValue("Test value 2");
        Step expectedStep = new Step("Test action 2", "Test value 2");
        Assertions.assertEquals(stepDto, expectedStep);
    }

    @Test
    public void testGetters(){
        Step actualStep = new Step("Test action 2", "Test value 2");
        stepDto.setValue(actualStep.getValue());
        stepDto.setAction(actualStep.getAction());
        Assertions.assertEquals(actualStep, stepDto);
    }

    @Test
    public void testEquals(){
        Step stepDto2 = new Step("Test action 2", "Test value 2");
        boolean result = stepDto.equals(stepDto2);
        Assertions.assertFalse(result);
        stepDto.setAction("Test action 2");
        stepDto.setValue("Test value 2");
        boolean result2 = stepDto.equals(stepDto2);
        Assertions.assertTrue(result2);
    }

}