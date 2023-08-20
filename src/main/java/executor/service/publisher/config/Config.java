package executor.service.publisher.config;

import executor.service.publisher.queue.ThreadSafeQueueHandler;
import executor.service.publisher.queue.proxy.ProxySourceQueueHandler;
import executor.service.publisher.queue.proxy.ProxySourceQueueHandlerImpl;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandler;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandlerImpl;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ProxySourceQueueHandler proxyQueueHandler() {
        return new ProxySourceQueueHandlerImpl(new ThreadSafeQueueHandler<>());
    }

    @Bean
    public ScenarioSourceQueueHandler scenarioQueueHandler() {
        return new ScenarioSourceQueueHandlerImpl(new ThreadSafeQueueHandler<>());
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();

    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("QUEUE_LOGGER");
    }
}
