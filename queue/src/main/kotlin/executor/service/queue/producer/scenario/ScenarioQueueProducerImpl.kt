package executor.service.queue.producer.scenario

import executor.service.model.Scenario
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class ScenarioQueueProducerImpl(
    private val template: RedisTemplate<String, Any>
) : ScenarioQueueProducer {

    private val key = "scenario.queue.key"

    override fun add(item: List<Scenario>) { template.opsForList().leftPush(key, item) }
}