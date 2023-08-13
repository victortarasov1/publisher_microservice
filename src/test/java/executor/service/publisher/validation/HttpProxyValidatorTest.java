package executor.service.publisher.validation;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxyCredentialsDTO;
import executor.service.publisher.model.ProxyNetworkConfigDTO;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HttpProxyValidatorTest {

    private ProxyValidator validator;

    @BeforeEach
    void setUp() {
        OkHttpClient client = new OkHttpClient();
        validator = new HttpProxyValidator(client);
    }

    @Test
    void testIsValid_shouldReturnTrue() {
        ProxyConfigHolderDto dto = new ProxyConfigHolderDto(new ProxyNetworkConfigDTO("20.210.113.32", 8080), new ProxyCredentialsDTO());
        boolean valid = validator.isValid(dto);
        assertThat(valid).isTrue();
    }

    @Test
    void testIsValid_shouldReturnFalse() {
        ProxyConfigHolderDto dto = new ProxyConfigHolderDto(new ProxyNetworkConfigDTO("20.201.114.32", 3030), new ProxyCredentialsDTO());
        boolean valid = validator.isValid(dto);
        assertThat(valid).isFalse();
    }

}