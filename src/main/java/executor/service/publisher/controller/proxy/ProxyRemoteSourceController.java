package executor.service.publisher.controller.proxy;

import executor.service.publisher.controller.RemoteSourceController;
import executor.service.publisher.model.ProxySourceDto;
import executor.service.publisher.processing.RemoteProcessingService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/publisher/proxy/remote")
public class ProxyRemoteSourceController implements RemoteSourceController<ProxySourceDto> {
    private final RemoteProcessingService<ProxySourceDto> service;

    public ProxyRemoteSourceController(RemoteProcessingService<ProxySourceDto> service) {
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
