package executor.service.queue.producer

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class JsonQueueAdder(
    private val template: StringRedisTemplate,
    private val mapper: ObjectMapper
) : QueueAdder {
    override fun <T> add(key: String, item: T) {
        mapper.writeValueAsString(item).run {
            template.opsForList().leftPush(key, this)
        }
    }
}