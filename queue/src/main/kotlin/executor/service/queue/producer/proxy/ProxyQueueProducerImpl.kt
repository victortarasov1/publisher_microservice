package executor.service.queue.producer.proxy

import executor.service.model.ProxyConfigHolder
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class ProxyQueueProducerImpl(
    private val template: RedisTemplate<String, Any>
) : ProxyQueueProducer {

    private val key = "proxy.queue.key"

    override fun add(item: ProxyConfigHolder) { template.opsForList().leftPush(key, item) }
}