package executor.service.publisher.model;

import java.util.Objects;

public class ProxyConfigHolderDto {

    private ProxyNetworkConfigDTO proxyNetworkConfig;

    private ProxyCredentialsDTO proxyCredentials;


    public ProxyConfigHolderDto() { }

    public ProxyConfigHolderDto(ProxyNetworkConfigDTO proxyNetworkConfig, ProxyCredentialsDTO proxyCredentials) {
        this.proxyNetworkConfig = proxyNetworkConfig;
        this.proxyCredentials = proxyCredentials;
    }

    public ProxyNetworkConfigDTO getProxyNetworkConfig() {
        return proxyNetworkConfig;
    }

    public void setProxyNetworkConfig(ProxyNetworkConfigDTO proxyNetworkConfig) {
        this.proxyNetworkConfig = proxyNetworkConfig;
    }

    public ProxyCredentialsDTO getProxyCredentials() {
        return proxyCredentials;
    }

    public void setProxyCredentials(ProxyCredentialsDTO proxyCredentials) {
        this.proxyCredentials = proxyCredentials;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProxyConfigHolderDto that = (ProxyConfigHolderDto) o;

        if (!Objects.equals(proxyNetworkConfig, that.proxyNetworkConfig)) return false;
        return Objects.equals(proxyCredentials, that.proxyCredentials);
    }

    @Override
    public int hashCode() {
        int result = proxyNetworkConfig.hashCode();
        result = 31 * result + proxyCredentials.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProxyConfigHolderDto{" +
                "proxyNetworkConfig=" + proxyNetworkConfig +
                ", proxyCredentials=" + proxyCredentials +
                '}';
    }
}