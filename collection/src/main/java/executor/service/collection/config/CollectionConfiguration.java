package executor.service.collection.config;


import executor.service.collection.queue.ThreadSafeQueueHandler;
import executor.service.collection.queue.proxy.ProxySourceQueueHandler;
import executor.service.collection.queue.proxy.ProxySourceQueueHandlerImpl;
import executor.service.collection.queue.report.ReportQueueHandler;
import executor.service.collection.queue.report.ReportQueueHandlerImpl;
import executor.service.collection.queue.scenario.ScenarioSourceQueueHandler;
import executor.service.collection.queue.scenario.ScenarioSourceQueueHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectionConfiguration {

    @Bean
    public ProxySourceQueueHandler proxyQueueHandler() {
        return new ProxySourceQueueHandlerImpl(new ThreadSafeQueueHandler<>());
    }

    @Bean
    public ScenarioSourceQueueHandler scenarioQueueHandler() {
        return new ScenarioSourceQueueHandlerImpl(new ThreadSafeQueueHandler<>());
    }

    @Bean
    public ReportQueueHandler reportQueueHandler() {
        return new ReportQueueHandlerImpl(new ThreadSafeQueueHandler<>());
    }
}
