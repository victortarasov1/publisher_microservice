package executor.service.validation;

import executor.service.model.ProxyConfigHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectProxyValidatorTest {

    private static final String TYPE = "direct";
    private ProxyValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DirectProxyValidator();
    }
    @Test
    void isValid() {
        ProxyConfigHolder proxy = new ProxyConfigHolder();
        boolean result = validator.isValid(proxy);
        assertThat(result).isFalse();
    }

    @Test
    void testGetType() {
        String result = validator.getType();
        assertThat(result).isEqualTo(TYPE);
    }
}