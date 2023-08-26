package executor.service.publisher.model;

import java.util.Objects;

public class ProxyNetworkConfig {
    private String hostname;
    private Integer port;

    public ProxyNetworkConfig() {
    }

    public ProxyNetworkConfig(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProxyNetworkConfig networkConfig)) return false;
        return Objects.equals(hostname, networkConfig.hostname)
                && Objects.equals(port, networkConfig.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostname, port);
    }

    @Override
    public String toString() {
        return "ProxyNetworkConfigDTO{" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                '}';
    }
}
