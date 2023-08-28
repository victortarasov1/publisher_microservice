package executor.service.publisher.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SecurityConfigTest {

    private static final String TEST_SECRET_KEY = "testSecretKey";
    private static final String DIFFERENT_SECRET_KEY = "differentSecretKey";

    @Test
    public void testDefaultConstructor() {
        SecurityConfig config = new SecurityConfig();
        assertThat(config.getSecretKey()).isNull();
    }

    @Test
    public void testParameterizedConstructor() {
        SecurityConfig config = new SecurityConfig(TEST_SECRET_KEY);
        assertThat(config.getSecretKey()).isEqualTo(TEST_SECRET_KEY);
    }

    @Test
    public void testSecretKeyGetterAndSetter() {
        SecurityConfig config = new SecurityConfig();
        config.setSecretKey(TEST_SECRET_KEY);
        assertThat(config.getSecretKey()).isEqualTo(TEST_SECRET_KEY);
    }

    @Test
    public void testEqualsWithNull() {
        SecurityConfig config1 = new SecurityConfig(TEST_SECRET_KEY);
        assertThat(config1).isNotEqualTo(null);
    }

    @Test
    public void testEqualsWithDifferentType() {
        SecurityConfig config1 = new SecurityConfig(TEST_SECRET_KEY);
        String differentType = "string";
        assertThat(config1).isNotEqualTo(differentType);
    }

    @Test
    public void testEqualsWithItself() {
        SecurityConfig config1 = new SecurityConfig(TEST_SECRET_KEY);
        assertThat(config1).isEqualTo(config1);
    }

    @Test
    public void testEqualsAndHashCodeForEqualConfigs() {
        SecurityConfig config1 = new SecurityConfig(TEST_SECRET_KEY);
        SecurityConfig config2 = new SecurityConfig(TEST_SECRET_KEY);

        assertThat(config1).isEqualTo(config2);
        assertThat(config1.hashCode()).isEqualTo(config2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeForDifferentConfigs() {
        SecurityConfig config1 = new SecurityConfig(TEST_SECRET_KEY);
        SecurityConfig config3 = new SecurityConfig(DIFFERENT_SECRET_KEY);

        assertThat(config1).isNotEqualTo(config3);
        assertThat(config1.hashCode()).isNotEqualTo(config3.hashCode());
    }

    @Test
    public void testToString() {
        SecurityConfig config = new SecurityConfig(TEST_SECRET_KEY);

        String expectedToString = "SecurityConfigDto{secretKey='" + TEST_SECRET_KEY + "'}";
        assertThat(config.toString()).isEqualTo(expectedToString);
    }
}
