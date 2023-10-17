package executor.service.publisher.source.service.proxy;

import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.model.ProxySource;
import executor.service.publisher.source.reader.FileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProxySourceServiceFile implements ProxySourceService {

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
