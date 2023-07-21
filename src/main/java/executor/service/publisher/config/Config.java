package executor.service.publisher.config;

import executor.service.publisher.controller.ScenarioSourceController;
import executor.service.publisher.controller.ScenarioSourceControllerImpl;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.queue.ProxySourceQueueHandler;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.queue.ScenarioSourceQueueHandler;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.ConcurrentLinkedQueue;

@Configuration
@PropertySource("classpath:proxy-api-connection.properties")
public class Config {
    @Bean
    public QueueHandler<ProxyConfigHolderDto> proxyQueueHandler() {
        return new ProxySourceQueueHandler(new ConcurrentLinkedQueue<>());
    }

    @Bean
    public QueueHandler<ScenarioDto> scenarioQueueHandler() {
        return new ScenarioSourceQueueHandler(new ConcurrentLinkedQueue<>());
    }

    @Bean
    public ScenarioSourceController scenarioSourceController(QueueHandler<ScenarioDto> handler) {
        return new ScenarioSourceControllerImpl(handler);
    }

    @Bean
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }
}
