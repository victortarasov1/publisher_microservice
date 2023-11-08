package executor.service.processing.controller.proxy;

import executor.service.processing.controller.RemoteSourceController;
import executor.service.processing.service.proxy.ProxyRemoteProcessingService;
import executor.service.source.model.ProxySource;
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
