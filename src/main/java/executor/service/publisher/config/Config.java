package executor.service.publisher.config;

import executor.service.publisher.queue.proxy.ProxySourceQueueHandler;
import executor.service.publisher.queue.proxy.ProxySourceQueueHandlerImpl;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandler;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandlerImpl;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentLinkedQueue;

@Configuration
public class Config {
    @Bean
    public ProxySourceQueueHandler proxyQueueHandler() {
        return new ProxySourceQueueHandlerImpl(new ConcurrentLinkedQueue<>());
    }

    @Bean
    public ScenarioSourceQueueHandler scenarioQueueHandler() {
        return new ScenarioSourceQueueHandlerImpl(new ConcurrentLinkedQueue<>());
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();

    }
}
