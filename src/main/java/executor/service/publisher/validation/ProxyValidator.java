package executor.service.publisher.validation;

import executor.service.publisher.model.ProxyConfigHolderDto;

public interface ProxyValidator {
    boolean isValid(ProxyConfigHolderDto dto, String proxyType);
}
