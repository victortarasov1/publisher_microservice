package executor.service.publisher.validation;

import executor.service.publisher.model.ProxyConfigHolder;

public interface ProxyValidator {
    boolean isValid(ProxyConfigHolder proxy);
    String getType();
}
