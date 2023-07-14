package executor.service.publisher.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StepDtoTest {

    private StepDto stepDto;

    @BeforeEach
    public void setUp(){
        stepDto = new StepDto("Test action", "Test value");
    }

    @Test
    public void testEmptyConstructor(){
        StepDto actualStepDto = new StepDto();
        StepDto expectedStepDto = new StepDto();
        Assertions.assertEquals(expectedStepDto, actualStepDto);
    }

    @Test
    public void testSetters(){
        stepDto.setAction("Test action 2");
        stepDto.setValue("Test value 2");
        StepDto expectedStepDto = new StepDto("Test action 2", "Test value 2");
        Assertions.assertEquals(stepDto, expectedStepDto);
    }

    @Test
    public void testGetters(){
        StepDto actualStepDto = new StepDto("Test action 2", "Test value 2");
        stepDto.setValue(actualStepDto.getValue());
        stepDto.setAction(actualStepDto.getAction());
        Assertions.assertEquals(actualStepDto, stepDto);
    }

    @Test
    public void testEquals(){
        StepDto stepDto2 = new StepDto("Test action 2", "Test value 2");
        boolean result = stepDto.equals(stepDto2);
        Assertions.assertFalse(result);
        stepDto.setAction("Test action 2");
        stepDto.setValue("Test value 2");
        boolean result2 = stepDto.equals(stepDto2);
        Assertions.assertTrue(result2);
    }

}