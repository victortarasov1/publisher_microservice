package executor.service.function;

import executor.service.model.ScenarioReport;
import executor.service.repository.ScenarioReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class MessageFunctions {
    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Consumer<ScenarioReport> saveReport(ScenarioReportRepository repository) {
        return scenarioReport -> {
            logger.info("Received scenario report: {}", scenarioReport);
            repository.save(scenarioReport);
        };
    }
}
