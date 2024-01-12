package executor.service.queue.producer.scenario

import executor.service.logger.annotation.Logged
import executor.service.model.Scenario
import executor.service.queue.producer.QueueWriter
import org.springframework.stereotype.Component

@Component
@Logged
internal class ScenarioProducerImpl(private val adder: QueueWriter) : ScenarioProducer {

    private val key = "scenario.queue.key"

    override fun add(item: Scenario) { adder.write(key, item) }
}