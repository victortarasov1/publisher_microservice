package executor.service.publisher.maintenance.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProxyEndpointValidatorTest {

    private ProxyEndpointValidator endpointValidator;

    @BeforeEach
    void setUp() {
        this.endpointValidator = Mockito.mock(ProxyEndpointValidator.class);
    }

    @Test
    void testSuccessfulValidation() {
        Mockito.when(this.endpointValidator.validate(Mockito.anyString())).thenReturn(true);

        Assertions.assertTrue(this.endpointValidator.validate(Mockito.anyString()));
    }

    @Test
    void testFailedValidation() {
        Mockito.when(this.endpointValidator.validate(Mockito.anyString())).thenReturn(false);

        Assertions.assertFalse(this.endpointValidator.validate(Mockito.anyString()));
    }

}
