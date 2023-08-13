package executor.service.publisher.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProxySourceDto {

    @Value("${remote.proxy.source}")
    private String proxySource;
    @Value("${remote.proxy.storage}")
    private String proxySourceType;
    @Value("${remote.proxy.type}")
    private String proxyType;

    public ProxySourceDto() {

    }

    public ProxySourceDto(String proxySource, String proxySourceType, String proxyType) {
        this.proxySource = proxySource;
        this.proxySourceType = proxySourceType;
        this.proxyType = proxyType;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ProxySourceDto proxySourceDto)) return false;
        return Objects.equals(proxySource, proxySourceDto.proxySource) &&
                Objects.equals(proxySourceType, proxySourceDto.proxySourceType) &&
                Objects.equals(proxyType, proxySourceDto.proxyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proxySource, proxySourceType, proxyType);
    }

    @Override
    public String toString() {
        return "ProxySourceDto{" +
                "proxySource='" + proxySource + '\'' +
                ", proxySourceType='" + proxySourceType + '\'' +
                ", proxyType='" + proxyType + '\'' +
                '}';
    }

    public String getProxySource() {
        return proxySource;
    }

    public void setProxySource(String proxySource) {
        this.proxySource = proxySource;
    }

    public String getProxySourceType() {
        return proxySourceType;
    }

    public void setProxySourceType(String proxySourceType) {
        this.proxySourceType = proxySourceType;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }
}
