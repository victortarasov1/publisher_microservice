package executor.service.publisher.source.service.proxy;

import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.model.ProxySource;
import executor.service.publisher.source.okhttp.OkhttpLoader;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProxySourceServiceUrl implements ProxySourceService {
    private final OkhttpLoader loader;

    @Override
    public List<ProxyConfigHolder> loadData(ProxySource source) {
        Request request = new Request.Builder().url(source.getSource()).get().build();
        return loader.loadData(request, RemoteProxyData.class).stream().map(RemoteProxyData::createProxyConfigHolder).toList();
    }

    @Override
    public String getType() {
        return "url";
    }

}
