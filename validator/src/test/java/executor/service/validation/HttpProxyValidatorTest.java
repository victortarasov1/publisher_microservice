package executor.service.validation;

import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyNetworkConfig;
import executor.service.validator.HttpProxyValidator;
import executor.service.validator.ProxyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HttpProxyValidatorTest {

    private ProxyValidator validator;
    private static final String TYPE = "http";

    @BeforeEach
    void setUp() {
        validator = new HttpProxyValidator();
    }

    @Test
    void testIsValid_shouldReturnTrue() {
        ProxyConfigHolder dto = new ProxyConfigHolder(new ProxyNetworkConfig("20.210.113.32", 8080));
        boolean valid = validator.isValid(dto);
        assertThat(valid).isTrue();
    }

    @Test
    void testIsValid_shouldReturnFalse() {
        ProxyConfigHolder dto = new ProxyConfigHolder(new ProxyNetworkConfig("20.201.114.32", 3030));
        boolean valid = validator.isValid(dto);
        assertThat(valid).isFalse();
    }

    @Test
    void testGetType() {
        String result = validator.getType();
        assertThat(result).isEqualTo(TYPE);
    }

}