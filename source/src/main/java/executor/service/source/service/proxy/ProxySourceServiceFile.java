package executor.service.source.service.proxy;

import executor.service.model.ProxyConfigHolder;
import executor.service.source.model.ProxySource;
import executor.service.source.reader.FileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
class ProxySourceServiceFile implements ProxySourceService {

    private final FileReader reader;

    @Override
    public List<ProxyConfigHolder> loadData(ProxySource source) {
        return reader.readData(source.getSource(), ProxyConfigHolder.class);
    }

    @Override
    public String getType() {
        return "file";
    }
}
