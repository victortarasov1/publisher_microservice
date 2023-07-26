package executor.service.publisher.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;


@PropertySource("classpath:application.properties")
public class ProxySourceServiceFileImpl implements ProxySourceServiceFile {
    private static final String PROPERTY_KEY_SOURCE_FILE_PATH = "executor.service.publisher.proxy";
    private static final String DEFAULT_SOURCE_FILE_PATH = "proxy.json";
     private final QueueHandler<ProxyConfigHolderDto> queueHandler;
    private final ObjectMapper mapper;
    private final Environment environment;
    private File file;

    public ProxySourceServiceFileImpl(QueueHandler<ProxyConfigHolderDto> queueHandler,
                                      ObjectMapper mapper,
                                      Environment environment) {
        this.queueHandler = queueHandler;
        this.mapper = mapper;
        this.environment = environment;
    }

    @Override
    public void setSourceFile(File file) {
        this.file = file;
    }

    @Override
    public void getProxy() throws IOException {
        if (file == null) {
            String filePath = environment.getProperty(PROPERTY_KEY_SOURCE_FILE_PATH, DEFAULT_SOURCE_FILE_PATH);
            file = new File(filePath);
        }
        queueHandler.addAll(List.of(readProxy(file).toArray(new ProxyConfigHolderDto[0])));
    }

    private Collection<ProxyConfigHolderDto> readProxy(File file) throws IOException {
        return mapper.readValue(
                file,
                mapper.getTypeFactory().constructCollectionType(Collection.class, ProxyConfigHolderDto.class)
        );
    }
}
