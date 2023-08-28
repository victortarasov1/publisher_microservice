package executor.service.publisher.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProxyConfigHolderTest {

    private static final String TEST_HOSTNAME = "testHostname";
    private static final Integer TEST_PORT = 8080;
    private static final String DIFFERENT_HOSTNAME = "differentHostname";
    private static final Integer DIFFERENT_PORT = 8888;
    private static final String TEST_USERNAME = "username";
    private static final String TEST_PASSWORD = "password";
    private static final String DIFFERENT_USERNAME = "differentUsername";
    private static final String DIFFERENT_PASSWORD = "differentPassword";

    @Test
    public void testDefaultConstructor() {
        ProxyNetworkConfig proxyNetworkConfig = new ProxyNetworkConfig();
        ProxyCredentials proxyCredentials = new ProxyCredentials();
        ProxyConfigHolder configHolder = new ProxyConfigHolder(proxyNetworkConfig, proxyCredentials);

        assertThat(configHolder.getProxyNetworkConfig()).isEqualTo(proxyNetworkConfig);
        assertThat(configHolder.getProxyCredentials()).isEqualTo(proxyCredentials);
    }

    @Test
    public void testParameterizedConstructor() {
        ProxyNetworkConfig proxyNetworkConfig = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        ProxyCredentials proxyCredentials = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        ProxyConfigHolder configHolder = new ProxyConfigHolder(proxyNetworkConfig, proxyCredentials);

        assertThat(configHolder.getProxyNetworkConfig()).isEqualTo(proxyNetworkConfig);
        assertThat(configHolder.getProxyCredentials()).isEqualTo(proxyCredentials);
    }
    @Test
    public void testConstructorWithNetworkConfig() {
        ProxyNetworkConfig proxyNetworkConfig = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        ProxyConfigHolder configHolder = new ProxyConfigHolder(proxyNetworkConfig);
        assertThat(configHolder.getProxyNetworkConfig()).isEqualTo(proxyNetworkConfig);
    }

    @Test
    public void testProxyNetworkConfigGetterAndSetter() {
        ProxyNetworkConfig proxyNetworkConfig1 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        ProxyConfigHolder configHolder = new ProxyConfigHolder();
        configHolder.setProxyNetworkConfig(proxyNetworkConfig1);

        assertThat(configHolder.getProxyNetworkConfig()).isEqualTo(proxyNetworkConfig1);
    }

    @Test
    public void testProxyCredentialsGetterAndSetter() {
        ProxyCredentials proxyCredentials1 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        ProxyConfigHolder configHolder = new ProxyConfigHolder();
        configHolder.setProxyCredentials(proxyCredentials1);

        assertThat(configHolder.getProxyCredentials()).isEqualTo(proxyCredentials1);
    }

    @Test
    public void testEqualsWithNull() {
        ProxyConfigHolder configHolder = new ProxyConfigHolder();
        assertThat(configHolder).isNotEqualTo(null);
    }

    @Test
    public void testEqualsWithDifferentType() {
        ProxyConfigHolder configHolder = new ProxyConfigHolder();
        String differentType = "string";
        assertThat(configHolder).isNotEqualTo(differentType);
    }

    @Test
    public void testEqualsWithItself() {
        ProxyConfigHolder configHolder = new ProxyConfigHolder();
        assertThat(configHolder).isEqualTo(configHolder);
    }

    @Test
    public void testEqualsAndHashCodeForEqualConfigHolders() {
        ProxyNetworkConfig proxyNetworkConfig1 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        ProxyCredentials proxyCredentials1 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        ProxyConfigHolder configHolder1 = new ProxyConfigHolder(proxyNetworkConfig1, proxyCredentials1);

        ProxyNetworkConfig proxyNetworkConfig2 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        ProxyCredentials proxyCredentials2 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        ProxyConfigHolder configHolder2 = new ProxyConfigHolder(proxyNetworkConfig2, proxyCredentials2);

        assertThat(configHolder1).isEqualTo(configHolder2);
        assertThat(configHolder1.hashCode()).isEqualTo(configHolder2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeForDifferentConfigHolders() {
        ProxyNetworkConfig proxyNetworkConfig1 = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        ProxyCredentials proxyCredentials1 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        ProxyConfigHolder configHolder1 = new ProxyConfigHolder(proxyNetworkConfig1, proxyCredentials1);

        ProxyNetworkConfig proxyNetworkConfig3 = new ProxyNetworkConfig(DIFFERENT_HOSTNAME, DIFFERENT_PORT);
        ProxyCredentials proxyCredentials3 = new ProxyCredentials(DIFFERENT_USERNAME, DIFFERENT_PASSWORD);
        ProxyConfigHolder configHolder3 = new ProxyConfigHolder(proxyNetworkConfig3, proxyCredentials3);

        assertThat(configHolder1).isNotEqualTo(configHolder3);
        assertThat(configHolder1.hashCode()).isNotEqualTo(configHolder3.hashCode());
    }

    @Test
    public void testToString() {
        ProxyNetworkConfig proxyNetworkConfig = new ProxyNetworkConfig(TEST_HOSTNAME, TEST_PORT);
        ProxyCredentials proxyCredentials = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        ProxyConfigHolder configHolder = new ProxyConfigHolder(proxyNetworkConfig, proxyCredentials);

        String expectedToString = "ProxyConfigHolderDto{proxyNetworkConfig=" + proxyNetworkConfig + ", proxyCredentials=" + proxyCredentials + '}';
        assertThat(configHolder.toString()).isEqualTo(expectedToString);
    }
}