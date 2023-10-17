package executor.service.publisher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProxySource {

    @Value("${remote.proxy.source}")
    private String source;
    @Value("${remote.proxy.storage}")
    private String storage;
    @Value("${remote.proxy.type}")
    private String type;

}
