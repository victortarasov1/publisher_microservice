package executor.service.publisher.controller.proxy;

import executor.service.publisher.controller.RemoteSourceController;
import executor.service.publisher.model.ProxySource;
import executor.service.publisher.processing.proxy.ProxyRemoteProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/publisher/proxy/remote")
@RequiredArgsConstructor
public class ProxyRemoteSourceController implements RemoteSourceController<ProxySource> {
    private final ProxyRemoteProcessingService service;

    @Override
    public void loadFromDefaultRemoteSource() {
        service.loadFromDefaultRemoteSource();
    }

    @Override
    public void loadFromCustomRemoteSource(ProxySource source) {
        service.loadFromCustomRemoteSource(source);
    }
}
