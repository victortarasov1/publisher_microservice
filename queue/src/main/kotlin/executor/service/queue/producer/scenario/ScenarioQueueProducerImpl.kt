package executor.service.queue.producer.scenario

import executor.service.logger.annotation.Logged
import executor.service.model.Scenario
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
@Logged
class ScenarioQueueProducerImpl(
    private val template: RedisTemplate<String, Any>
) : ScenarioQueueProducer {

    private val key = "scenario.queue.key"

    override fun add(item: Scenario) { template.opsForList().leftPush(key, item) }
}