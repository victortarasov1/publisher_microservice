package executor.service.source.service.proxy;

import executor.service.model.ProxyConfigHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoteProxyDataTest {
    @Test
    void testConstructor_shouldStripWhitespaceFromIp() {
        RemoteProxyData remoteProxyData = new RemoteProxyData(null, null, "  127.0.0.1  ", 8080);

        Assertions.assertThat(remoteProxyData.ip()).isEqualTo("127.0.0.1");
    }

    @Test
    void testConstructor_shouldStripWhitespaceFromCredentials() {
        RemoteProxyData remoteProxyData = new RemoteProxyData("  testUser  ", "  testPass  ", "127.0.0.1", 8080);

        Assertions.assertThat(remoteProxyData.username()).isEqualTo("testUser");
        Assertions.assertThat(remoteProxyData.password()).isEqualTo("testPass");
    }

    @Test
    void testCreateProxyConfigHolder_shouldCreateConfigHolderWithCredentials() {
        RemoteProxyData remoteProxyData = new RemoteProxyData("  testUser  ", "  testPass  ", "127.0.0.1", 8080);
        ProxyConfigHolder holder = remoteProxyData.createProxyConfigHolder();

        Assertions.assertThat(holder.getProxyNetworkConfig().getHostname()).isEqualTo("127.0.0.1");
        Assertions.assertThat(holder.getProxyNetworkConfig().getPort()).isEqualTo(8080);
        Assertions.assertThat(holder.getProxyCredentials().getUsername()).isEqualTo("testUser");
        Assertions.assertThat(holder.getProxyCredentials().getPassword()).isEqualTo("testPass");
    }

    @Test
    void testCreateProxyConfigHolder_shouldCreateConfigHolderWithoutCredentials() {
        RemoteProxyData remoteProxyData = new RemoteProxyData(null, null, "  127.0.0.1  ", 8080);
        ProxyConfigHolder holder = remoteProxyData.createProxyConfigHolder();

        Assertions.assertThat(holder.getProxyNetworkConfig().getHostname()).isEqualTo("127.0.0.1");
        Assertions.assertThat(holder.getProxyNetworkConfig().getPort()).isEqualTo(8080);
        Assertions.assertThat(holder.getProxyCredentials()).isNull();
    }
}