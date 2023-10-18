package executor.service.processing.proxy;

import executor.service.exception.validator.UnknownProxyTypeException;
import executor.service.processing.ProcessingService;
import executor.service.model.ProxyConfigHolder;

import java.util.List;

public interface ProxyProcessingService extends ProcessingService<ProxyConfigHolder> {
    /**
     * Adds a proxy to the queue after asynchronous validation.
     *
     * @param proxy the proxy to be added
     * @throws UnknownProxyTypeException if the default proxy source type is unknown
     */
    void add(ProxyConfigHolder proxy);

    /**
     * Adds a list of proxies to the queue after asynchronous validation.
     *
     * @param proxies the list of proxies to be added
     * @throws UnknownProxyTypeException if the default proxy source type is unknown
     */
    void addAll(List<ProxyConfigHolder> proxies);
}
