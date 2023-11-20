package executor.service.queue.producer.scenario

import executor.service.logger.annotation.Logged
import executor.service.model.Scenario
import executor.service.queue.producer.QueueAdder
import org.springframework.stereotype.Component

@Component
@Logged
class ScenarioQueueProducerImpl(private val adder: QueueAdder) : ScenarioQueueProducer {

    private val key = "scenario.queue.key"

    override fun add(item: Scenario) { adder.add(key, item) }
}