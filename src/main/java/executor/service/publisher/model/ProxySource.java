package executor.service.publisher.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProxySource {

    @Value("${remote.proxy.source}")
    private String source;
    @Value("${remote.proxy.storage}")
    private String storage;
    @Value("${remote.proxy.type}")
    private String type;

    public ProxySource() {

    }

    public ProxySource(String source, String storage, String type) {
        this.source = source;
        this.storage = storage;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProxySource proxySource)) return false;
        return Objects.equals(source, proxySource.source) &&
                Objects.equals(storage, proxySource.storage) &&
                Objects.equals(type, proxySource.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, storage, type);
    }

    @Override
    public String toString() {
        return "ProxySourceDto{" +
                "proxySource='" + source + '\'' +
                ", proxySourceType='" + storage + '\'' +
                ", proxyType='" + type + '\'' +
                '}';
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
