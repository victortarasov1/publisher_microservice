package executor.service.queue.producer

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class JsonQueueWriter(
    private val template: StringRedisTemplate,
    private val mapper: ObjectMapper
) : QueueWriter {
    override fun <T> write(key: String, item: T) {
        val json = mapper.writeValueAsString(item)
        template.opsForList().leftPush(key, json)
    }
}