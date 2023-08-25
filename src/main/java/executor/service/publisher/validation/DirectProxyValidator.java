package executor.service.publisher.validation;

import executor.service.publisher.model.ProxyConfigHolder;
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
