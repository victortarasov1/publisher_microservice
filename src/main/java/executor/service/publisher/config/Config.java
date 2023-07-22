package executor.service.publisher.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.publisher.controller.ProxySourceController;
import executor.service.publisher.controller.ProxySourceControllerImpl;
import executor.service.publisher.controller.ScenarioSourceController;
import executor.service.publisher.controller.ScenarioSourceControllerImpl;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.proxy.ProxySourceServiceFile;
import executor.service.publisher.proxy.ProxySourceServiceFileImpl;
import executor.service.publisher.queue.ProxySourceQueueHandler;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.queue.ScenarioSourceQueueHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Configuration
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
    public ProxySourceController proxySourceController(QueueHandler<ProxyConfigHolderDto> proxyHandler) {
        return new ProxySourceControllerImpl(proxyHandler);
    }

    @Bean
    public ProxySourceServiceFile proxySourceServiceFileImpl(QueueHandler<ProxyConfigHolderDto> queueHandler, ObjectMapper mapper, Environment environment) {
        return new ProxySourceServiceFileImpl(queueHandler, mapper, environment);
    }
}
