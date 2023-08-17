package executor.service.publisher.processing.proxy;

import executor.service.publisher.exception.validator.UnknownProxyTypeException;
import executor.service.publisher.model.ProxyConfigHolderDto;

import java.util.List;

public interface ProxyProcessingService {
    /**
     * Adds a proxy to the queue after asynchronous validation.
     *
     * @param dto the proxy to be added
     * @throws UnknownProxyTypeException if the default proxy source type is unknown
     */
    void add(ProxyConfigHolderDto dto);

    /**
     * Adds a list of proxies to the queue after asynchronous validation.
     *
     * @param dtoList the list of proxies to be added
     * @throws UnknownProxyTypeException if the default proxy source type is unknown
     */
    void addAll(List<ProxyConfigHolderDto> dtoList);
}
