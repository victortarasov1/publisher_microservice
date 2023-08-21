package executor.service.publisher.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

class LoggingAspectTest {
    private static final String METHOD_NAME = "testMethod";
    private static final String CLASS_NAME = "TestClass";
    private Logger logger;

    private JoinPoint joinPoint;

    private Signature signature;

    private LoggingAspect loggingAspect;

    @BeforeEach
    void setup() {
        logger = mock(Logger.class);
        joinPoint = mock(JoinPoint.class);
        signature = mock(Signature.class);
        when(joinPoint.getSignature()).thenReturn(signature);
        loggingAspect = new LoggingAspect(logger);
    }

    @Test
    void testLogBefore_shouldLogMethodExecution() {
        Object[] args = {1, "arg2"};

        when(signature.getName()).thenReturn(METHOD_NAME);
        when(joinPoint.getTarget()).thenReturn(new TestClass());
        when(joinPoint.getArgs()).thenReturn(args);

        loggingAspect.logBefore(joinPoint);

        verify(logger).info(eq(LogMessage.EXECUTING_METHOD.getMessage()), eq(METHOD_NAME), eq(CLASS_NAME), eq(args));
    }

    @Test
    void testLogAfter_shouldLogMethodExecutionCompleted() {
        when(joinPoint.getTarget()).thenReturn(new TestClass());
        when(signature.getName()).thenReturn(METHOD_NAME);
        loggingAspect.logAfterReturning(joinPoint, null);
        verify(logger).info(eq(LogMessage.METHOD_EXECUTION_COMPLETED.getMessage()), eq(METHOD_NAME), eq(CLASS_NAME));
    }

    @Test
    void testLogAfter_shouldLogMethodReturnValue() {
        when(joinPoint.getTarget()).thenReturn(new TestClass());
        when(signature.getName()).thenReturn(METHOD_NAME);
        int result = 5;
        loggingAspect.logAfterReturning(joinPoint, result);
        verify(logger).info(eq(LogMessage.METHOD_RETURN_VALUE.getMessage()), eq(METHOD_NAME), eq(CLASS_NAME), eq(result));
    }

    private static class TestClass {

    }
}