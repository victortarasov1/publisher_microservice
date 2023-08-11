package executor.service.publisher.source;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.source.reader.FileReader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ProxySourceServiceFile implements SourceService {

    private final FileReader reader;
    private final QueueHandler<ProxyConfigHolderDto> queueHandler;

    private final String path;

    public ProxySourceServiceFile(FileReader reader, QueueHandler<ProxyConfigHolderDto> queueHandler, @Value("${source.proxy.file}") String path) {
        this.reader = reader;
        this.queueHandler = queueHandler;
        this.path = path;
    }

    @Override
    @PostConstruct
    public void loadData() {
        queueHandler.addAll(reader.readData(path, ProxyConfigHolderDto.class));
    }
}
