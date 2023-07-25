package executor.service.publisher.proxy;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public interface ProxySourceServiceFile {
    void setSourceFile(File file);

    void getProxy() throws IOException;
}
