package executor.service.publisher.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProxyConfigHolderTest {

    private ProxyConfigHolder proxyConfigHolderWithNullArgs;
    private ProxyConfigHolder proxyConfigHolderWithArgs;
    private ProxyConfigHolder proxyConfigHolderWithNoArgs;

    private static final ProxyNetworkConfig PROXY_NETWORK_CONFIG_MOCK = Mockito.mock(ProxyNetworkConfig.class);
    private static final ProxyCredentials PROXY_CREDENTIALS_MOCK = Mockito.mock(ProxyCredentials.class);


    @BeforeEach
    void setUp() {
        this.proxyConfigHolderWithArgs = new ProxyConfigHolder(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        this.proxyConfigHolderWithNoArgs = new ProxyConfigHolder();

        this.proxyConfigHolderWithNullArgs = new ProxyConfigHolder(null, null);
    }

    @Test
    void testDefaultConstructor() {
        Assertions.assertNotNull(this.proxyConfigHolderWithNoArgs);
    }

    @Test
    void testArgsConstructorWithNulls() {
        Assertions.assertNotNull(this.proxyConfigHolderWithNullArgs);
        Assertions.assertNull(this.proxyConfigHolderWithNullArgs.getProxyNetworkConfig());
        Assertions.assertNull(this.proxyConfigHolderWithNullArgs.getProxyCredentials());
    }

    @Test
    void testArgsConstructor() {
        Assertions.assertNotNull(this.proxyConfigHolderWithArgs);

        ProxyConfigHolder actual = new ProxyConfigHolder(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(this.proxyConfigHolderWithArgs, actual);
    }

    @Test
    void testSetProxyNetworkConfigWithNotNullArg() {
        this.proxyConfigHolderWithNoArgs.setProxyNetworkConfig(PROXY_NETWORK_CONFIG_MOCK);

        Assertions.assertEquals(PROXY_NETWORK_CONFIG_MOCK, this.proxyConfigHolderWithNoArgs.getProxyNetworkConfig());
    }

    @Test
    void testSetProxyNetworkConfigWithNullArg() {
        this.proxyConfigHolderWithNoArgs.setProxyNetworkConfig(null);

        Assertions.assertNull(this.proxyConfigHolderWithNoArgs.getProxyNetworkConfig());
    }

    @Test
    void testSetProxyCredentialsWithNotNullArg() {
        this.proxyConfigHolderWithNoArgs.setProxyCredentials(PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(PROXY_CREDENTIALS_MOCK, this.proxyConfigHolderWithNoArgs.getProxyCredentials());
    }

    @Test
    void testSetProxyCredentialsWithNullArg() {
        this.proxyConfigHolderWithNoArgs.setProxyCredentials(null);

        Assertions.assertNull(this.proxyConfigHolderWithNoArgs.getProxyCredentials());
    }

    @Test
    void testGetProxyNetworkConfig() {
        Assertions.assertEquals(PROXY_NETWORK_CONFIG_MOCK, this.proxyConfigHolderWithArgs.getProxyNetworkConfig());
    }

    @Test
    void testGetProxyCredentials() {
        Assertions.assertEquals(PROXY_CREDENTIALS_MOCK, this.proxyConfigHolderWithArgs.getProxyCredentials());
    }

    @Test
    void testEqualObjects() {
        ProxyConfigHolder actual = new ProxyConfigHolder(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(this.proxyConfigHolderWithArgs, actual);
    }

    @Test
    void testNotEqualObjects() {
        Assertions.assertNotEquals(this.proxyConfigHolderWithNullArgs, this.proxyConfigHolderWithArgs);
    }

    @Test
    void testHashcode() {
        ProxyConfigHolder actual = new ProxyConfigHolder(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(this.proxyConfigHolderWithArgs.hashCode(), actual.hashCode());
    }

    @Test
    void testToString() {
        ProxyConfigHolder actual = new ProxyConfigHolder(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(this.proxyConfigHolderWithArgs.toString(), actual.toString());
    }

}