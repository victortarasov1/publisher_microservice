package executor.service.validation;

import executor.service.model.ProxyConfigHolder;

public interface ProxyValidator {
    boolean isValid(ProxyConfigHolder proxy);
    String getType();
}
