package executor.service.validation;

import executor.service.model.ProxyConfigHolder;
import org.springframework.stereotype.Component;

@Component
public class DirectProxyValidator implements ProxyValidator {

    @Override
    public boolean isValid(ProxyConfigHolder proxy) {
        return false;
    }

    @Override
    public String getType() {
        return "direct";
    }
}
