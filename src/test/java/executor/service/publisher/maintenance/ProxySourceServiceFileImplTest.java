package executor.service.publisher.maintenance;

import executor.service.publisher.maintenance.ProxySourceServiceFile;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxyCredentialsDTO;
import executor.service.publisher.model.ProxyNetworkConfigDTO;
import executor.service.publisher.queue.QueueHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProxySourceServiceFileImplTest {
    private ProxySourceServiceFile proxySourceServiceFile;
    private QueueHandler<ProxyConfigHolderDto> proxySourceQueueHandler;
    private List<ProxyConfigHolderDto> proxyLsit;
    private File file = new File("testProxy.json");

    @Autowired
    private void getBeans(QueueHandler<ProxyConfigHolderDto> proxySourceQueueHandler, ProxySourceServiceFile proxySourceServiceFile) {
        this.proxySourceQueueHandler = proxySourceQueueHandler;
        this.proxySourceServiceFile = proxySourceServiceFile;
    }

    @BeforeEach
    void setUp() {
        proxyLsit = List.of(
                new ProxyConfigHolderDto(
                        new ProxyNetworkConfigDTO("host_0", 0),
                        new ProxyCredentialsDTO("user_0", "000")),
                new ProxyConfigHolderDto(
                        new ProxyNetworkConfigDTO("host_1", 1),
                        new ProxyCredentialsDTO("user_1", "111")),
                new ProxyConfigHolderDto(
                        new ProxyNetworkConfigDTO("host_2", 2),
                        new ProxyCredentialsDTO("user_2", "222"))
        );

        proxySourceServiceFile.setSourceFile(file);
    }

    @Test
    void testGetProxy() throws IOException {
        proxySourceServiceFile.getProxy();

        assertEquals(proxyLsit.get(0), proxySourceQueueHandler.poll().orElseThrow());
        assertEquals(proxyLsit.get(1), proxySourceQueueHandler.poll().orElseThrow());
        assertEquals(proxyLsit.get(2), proxySourceQueueHandler.poll().orElseThrow());

    }
}