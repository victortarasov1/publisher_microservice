package executor.service.publisher.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProxyNetworkConfigTest {
    private static final String EXPECTED_HOSTNAME = "Test";
    private static final Integer EXPECTED_PORT = 1234;
    private static final String EXPECTED_TO_STRING
            = "ProxyNetworkConfigDTO{hostname='Test', port=1234}";
    private ProxyNetworkConfig proxyNetworkConfigWithNoArgs;
    private ProxyNetworkConfig proxyNetworkConfigWithNullArgs;
    private ProxyNetworkConfig proxyNetworkConfigWithArgs;


    @BeforeEach
    public void setup(){
        proxyNetworkConfigWithNoArgs = new ProxyNetworkConfig();
        proxyNetworkConfigWithNullArgs = new ProxyNetworkConfig(null, null);
        proxyNetworkConfigWithArgs = new ProxyNetworkConfig(EXPECTED_HOSTNAME, EXPECTED_PORT);
    }

    @Test
    public void testDefaultConstructor(){
        assertNotEquals(null, proxyNetworkConfigWithNoArgs);
    }

    @Test
    public void testArgsConstructorWithNulls(){
        assertNotNull(proxyNetworkConfigWithNullArgs);
        assertNull(proxyNetworkConfigWithNullArgs.getHostname());
        assertNull(proxyNetworkConfigWithNullArgs.getHostname());
    }

    @Test
    public void testArgsConstructor(){
        assertNotNull(proxyNetworkConfigWithArgs);
        assertEquals(EXPECTED_HOSTNAME, proxyNetworkConfigWithArgs.getHostname());
        assertEquals(EXPECTED_PORT, proxyNetworkConfigWithArgs.getPort());
    }

    @Test
    public void testSetters(){
        proxyNetworkConfigWithNoArgs.setHostname(EXPECTED_HOSTNAME);
        proxyNetworkConfigWithNoArgs.setPort(EXPECTED_PORT);
        assertEquals(EXPECTED_HOSTNAME, proxyNetworkConfigWithNoArgs.getHostname());
        assertEquals(EXPECTED_PORT, proxyNetworkConfigWithNoArgs.getPort());
    }

    @Test
    public void testSettersWithNull(){
        proxyNetworkConfigWithNoArgs.setHostname(null);
        proxyNetworkConfigWithNoArgs.setPort(null);
        assertNull(proxyNetworkConfigWithNoArgs.getHostname());
        assertNull(proxyNetworkConfigWithNoArgs.getPort());
    }

    @Test
    public void testGetters(){
        assertEquals(EXPECTED_HOSTNAME, proxyNetworkConfigWithArgs.getHostname());
        assertEquals(EXPECTED_PORT, proxyNetworkConfigWithArgs.getPort());
    }

    @Test
    public void testEqualsSameObjects() {
        ProxyNetworkConfig expected = new ProxyNetworkConfig
                (proxyNetworkConfigWithArgs.getHostname(), proxyNetworkConfigWithArgs.getPort());
        assertEquals(expected, proxyNetworkConfigWithArgs);
    }

    @Test
    public void testEqualsDifferentObjects() {
        ProxyNetworkConfig expected = new ProxyNetworkConfig("hostname",0);
        assertNotEquals(expected, proxyNetworkConfigWithArgs);
    }
    @Test
    public void testHashCode() {
        ProxyNetworkConfig expected = new ProxyNetworkConfig("hostname",0);
        assertNotEquals(expected.hashCode(), proxyNetworkConfigWithArgs.hashCode());
    }

    @Test
    public void testToString(){
        assertEquals(proxyNetworkConfigWithArgs.toString(), EXPECTED_TO_STRING);
    }

}
