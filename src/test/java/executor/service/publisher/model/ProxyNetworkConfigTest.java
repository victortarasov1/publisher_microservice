package executor.service.publisher.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class ProxyNetworkConfigTest {

    private static final String TEST_HOSTNAME = "testHostname";
    private static final Integer TEST_PORT = 8080;
    private static final String DIFFERENT_HOSTNAME = "differentHostname";
    private static final Integer DIFFERENT_PORT = 8888;

    @Test
    public void testDefaultConstructor() {
        ProxyNetworkConfig config = new ProxyNetworkConfig();
        assertThat(config.getHostname()).isNull();
        assertThat(config.getPort()).isNull();
    }

    @Test
    public void testParameterizedConstructor() {
        ProxyNetworkConfig config = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);

        assertThat(config.getHostname()).isEqualTo(TEST_HOSTNAME);
        assertThat(config.getPort()).isEqualTo(TEST_PORT);
    }

    @Test
    public void testHostnameGetterAndSetter() {
        ProxyNetworkConfig config = new ProxyNetworkConfig();
        config.setHostname(TEST_HOSTNAME);
        assertThat(config.getHostname()).isEqualTo(TEST_HOSTNAME);
    }

    @Test
    public void testPortGetterAndSetter() {
        ProxyNetworkConfig config = new ProxyNetworkConfig();
        config.setPort(TEST_PORT);
        assertThat(config.getPort()).isEqualTo(TEST_PORT);
    }

    @Test
    public void testEqualsWithNull() {
        ProxyNetworkConfig config1 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        assertThat(config1).isNotEqualTo(null);
    }

    @Test
    public void testEqualsWithDifferentType() {
        ProxyNetworkConfig config1 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        String differentType = "string";
        assertThat(config1).isNotEqualTo(differentType);
    }

    @Test
    public void testEqualsWithItself() {
        ProxyNetworkConfig config1 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        assertThat(config1).isEqualTo(config1);
    }

    @Test
    public void testEqualsAndHashCodeForEqualConfigs() {
        ProxyNetworkConfig config1 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        ProxyNetworkConfig config2 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);

        assertThat(config1).isEqualTo(config2);
        assertThat(config1.hashCode()).isEqualTo(config2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeForDifferentConfigs() {
        ProxyNetworkConfig config1 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        ProxyNetworkConfig config3 = new ProxyNetworkConfig(DIFFERENT_HOSTNAME, DIFFERENT_PORT);

        assertThat(config1).isNotEqualTo(config3);
        assertThat(config1.hashCode()).isNotEqualTo(config3.hashCode());
    }

    @Test
    public void testToString() {
        ProxyNetworkConfig config = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);

        String expectedToString = "ProxyNetworkConfigDTO{hostname='" + TEST_HOSTNAME + "', port=" + TEST_PORT + '}';
        assertThat(config.toString()).isEqualTo(expectedToString);
    }

}
