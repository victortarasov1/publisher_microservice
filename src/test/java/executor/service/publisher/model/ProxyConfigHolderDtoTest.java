package executor.service.publisher.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProxyConfigHolderDtoTest {

    private ProxyConfigHolderDto proxyConfigHolderDtoWithNullArgs;
    private ProxyConfigHolderDto proxyConfigHolderDtoWithArgs;
    private ProxyConfigHolderDto proxyConfigHolderDtoWithNoArgs;

    private static final ProxyNetworkConfigDTO PROXY_NETWORK_CONFIG_MOCK = Mockito.mock(ProxyNetworkConfigDTO.class);
    private static final ProxyCredentialsDTO PROXY_CREDENTIALS_MOCK = Mockito.mock(ProxyCredentialsDTO.class);


    @BeforeEach
    void setUp() {
        this.proxyConfigHolderDtoWithArgs = new ProxyConfigHolderDto(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        this.proxyConfigHolderDtoWithNoArgs = new ProxyConfigHolderDto();

        this.proxyConfigHolderDtoWithNullArgs = new ProxyConfigHolderDto(null, null);
    }

    @Test
    void testDefaultConstructor() {
        Assertions.assertNotNull(this.proxyConfigHolderDtoWithNoArgs);
    }

    @Test
    void testArgsConstructorWithNulls() {
        Assertions.assertNotNull(this.proxyConfigHolderDtoWithNullArgs);
        Assertions.assertNull(this.proxyConfigHolderDtoWithNullArgs.getProxyNetworkConfig());
        Assertions.assertNull(this.proxyConfigHolderDtoWithNullArgs.getProxyCredentials());
    }

    @Test
    void testArgsConstructor() {
        Assertions.assertNotNull(this.proxyConfigHolderDtoWithArgs);

        ProxyConfigHolderDto actual = new ProxyConfigHolderDto(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(this.proxyConfigHolderDtoWithArgs, actual);
    }

    @Test
    void testSetProxyNetworkConfigWithNotNullArg() {
        this.proxyConfigHolderDtoWithNoArgs.setProxyNetworkConfig(PROXY_NETWORK_CONFIG_MOCK);

        Assertions.assertEquals(PROXY_NETWORK_CONFIG_MOCK, this.proxyConfigHolderDtoWithNoArgs.getProxyNetworkConfig());
    }

    @Test
    void testSetProxyNetworkConfigWithNullArg() {
        this.proxyConfigHolderDtoWithNoArgs.setProxyNetworkConfig(null);

        Assertions.assertNull(this.proxyConfigHolderDtoWithNoArgs.getProxyNetworkConfig());
    }

    @Test
    void testSetProxyCredentialsWithNotNullArg() {
        this.proxyConfigHolderDtoWithNoArgs.setProxyCredentials(PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(PROXY_CREDENTIALS_MOCK, this.proxyConfigHolderDtoWithNoArgs.getProxyCredentials());
    }

    @Test
    void testSetProxyCredentialsWithNullArg() {
        this.proxyConfigHolderDtoWithNoArgs.setProxyCredentials(null);

        Assertions.assertNull(this.proxyConfigHolderDtoWithNoArgs.getProxyCredentials());
    }

    @Test
    void testGetProxyNetworkConfig() {
        Assertions.assertEquals(PROXY_NETWORK_CONFIG_MOCK, this.proxyConfigHolderDtoWithArgs.getProxyNetworkConfig());
    }

    @Test
    void testGetProxyCredentials() {
        Assertions.assertEquals(PROXY_CREDENTIALS_MOCK, this.proxyConfigHolderDtoWithArgs.getProxyCredentials());
    }

    @Test
    void testEqualObjects() {
        ProxyConfigHolderDto actual = new ProxyConfigHolderDto(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(this.proxyConfigHolderDtoWithArgs, actual);
    }

    @Test
    void testNotEqualObjects() {
        Assertions.assertNotEquals(this.proxyConfigHolderDtoWithNullArgs, this.proxyConfigHolderDtoWithArgs);
    }

    @Test
    void testHashcode() {
        ProxyConfigHolderDto actual = new ProxyConfigHolderDto(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(this.proxyConfigHolderDtoWithArgs.hashCode(), actual.hashCode());
    }

    @Test
    void testToString() {
        ProxyConfigHolderDto actual = new ProxyConfigHolderDto(PROXY_NETWORK_CONFIG_MOCK, PROXY_CREDENTIALS_MOCK);

        Assertions.assertEquals(this.proxyConfigHolderDtoWithArgs.toString(), actual.toString());
    }

}