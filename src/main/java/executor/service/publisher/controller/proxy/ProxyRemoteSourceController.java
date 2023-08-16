package executor.service.publisher.controller.proxy;

import executor.service.publisher.controller.RemoteSourceController;
import executor.service.publisher.model.ProxySourceDto;
import executor.service.publisher.processing.proxy.ProxyRemoteProcessingService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/publisher/proxy/remote")
public class ProxyRemoteSourceController implements RemoteSourceController<ProxySourceDto> {
    private final ProxyRemoteProcessingService service;

    public ProxyRemoteSourceController(ProxyRemoteProcessingService service) {
        this.service = service;
    }

    @Override
    public void loadFromDefaultRemoteSource() {
        service.loadFromDefaultRemoteSource();
    }

    @Override
    public void loadFromCustomRemoteSource(ProxySourceDto dto) {
        service.loadFromCustomRemoteSource(dto);
    }
}
