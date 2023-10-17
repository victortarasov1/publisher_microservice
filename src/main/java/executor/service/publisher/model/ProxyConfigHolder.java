package executor.service.publisher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProxyConfigHolder {
    private ProxyNetworkConfig proxyNetworkConfig;

    private ProxyCredentials proxyCredentials;
    public ProxyConfigHolder(ProxyNetworkConfig proxyNetworkConfig) {
        this.proxyNetworkConfig = proxyNetworkConfig;
    }

}