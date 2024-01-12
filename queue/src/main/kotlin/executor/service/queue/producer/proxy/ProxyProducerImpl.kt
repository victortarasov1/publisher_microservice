package executor.service.queue.producer.proxy

import executor.service.logger.annotation.Logged
import executor.service.model.ProxyConfigHolder
import executor.service.queue.producer.QueueWriter
import org.springframework.stereotype.Component

@Component
@Logged
internal class ProxyProducerImpl(
    private val adder: QueueWriter
) : ProxyProducer {

    private val key = "proxy.queue.key"

    override fun add(item: ProxyConfigHolder) { adder.write(key, item) }
}