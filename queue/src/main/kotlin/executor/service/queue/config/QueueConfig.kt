package executor.service.queue.config

import executor.service.queue.model.RedisConfigHolder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer


@Configuration
class QueueConfig(private val holder: RedisConfigHolder) {

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration(holder.redisHost, holder.redisPort)
        val jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build()
        return JedisConnectionFactory(config, jedisClientConfiguration).apply { afterPropertiesSet() }
    }

    @Bean
    fun redisTemplate() = RedisTemplate<String, Any>().apply {
        connectionFactory = jedisConnectionFactory()
        valueSerializer = GenericJackson2JsonRedisSerializer()
    }
}