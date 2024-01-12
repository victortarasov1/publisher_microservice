package executor.service.queue.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import executor.service.queue.model.RedisConfigHolder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate


@Configuration
internal class QueueConfig(private val holder: RedisConfigHolder) {

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration(holder.redisHost, holder.redisPort)
        val jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build()
        return JedisConnectionFactory(config, jedisClientConfiguration).apply { afterPropertiesSet() }
    }

    @Bean
    fun redisTemplate() = StringRedisTemplate().apply { connectionFactory = jedisConnectionFactory() }

    @Bean
    fun mapper() = ObjectMapper().apply { registerModule(JavaTimeModule()) }

}