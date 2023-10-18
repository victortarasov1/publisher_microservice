package executor.service.validator;

import executor.service.model.ProxyConfigHolder;

public interface ProxyValidator {
    boolean isValid(ProxyConfigHolder proxy);
    String getType();
}
