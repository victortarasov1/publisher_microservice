package executor.service.publisher.validation;

import executor.service.publisher.model.ProxyConfigHolderDto;
import org.springframework.stereotype.Component;

@Component
public class DirectProxyValidator implements ProxyValidator {

    @Override
    public boolean isValid(ProxyConfigHolderDto dto) {
        return false;
    }

    @Override
    public String getType() {
        return "direct";
    }
}
