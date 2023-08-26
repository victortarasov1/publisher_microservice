package executor.service.publisher.model;

import java.util.Objects;

public class ProxyConfigHolder {

    private ProxyNetworkConfig proxyNetworkConfig;

    private ProxyCredentials proxyCredentials;


    public ProxyConfigHolder() {
    }

    public ProxyConfigHolder(ProxyNetworkConfig proxyNetworkConfig, ProxyCredentials proxyCredentials) {
        this.proxyNetworkConfig = proxyNetworkConfig;
        this.proxyCredentials = proxyCredentials;
    }

    public ProxyNetworkConfig getProxyNetworkConfig() {
        return proxyNetworkConfig;
    }

    public void setProxyNetworkConfig(ProxyNetworkConfig proxyNetworkConfig) {
        this.proxyNetworkConfig = proxyNetworkConfig;
    }

    public ProxyCredentials getProxyCredentials() {
        return proxyCredentials;
    }

    public void setProxyCredentials(ProxyCredentials proxyCredentials) {
        this.proxyCredentials = proxyCredentials;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProxyConfigHolder holder)) return false;
        return Objects.equals(proxyNetworkConfig, holder.proxyNetworkConfig)
                && Objects.equals(proxyCredentials, holder.proxyCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proxyNetworkConfig, proxyCredentials);
    }

    @Override
    public String toString() {
        return "ProxyConfigHolderDto{" +
                "proxyNetworkConfig=" + proxyNetworkConfig +
                ", proxyCredentials=" + proxyCredentials +
                '}';
    }
}