package executor.service.queue.producer.proxy

import executor.service.logger.annotation.Logged
import executor.service.model.ProxyConfigHolder
import executor.service.queue.producer.QueueAdder
import org.springframework.stereotype.Component

@Component
@Logged
class ProxyQueueProducerImpl(
    private val adder: QueueAdder
) : ProxyQueueProducer {

    private val key = "proxy.queue.key"

    override fun add(item: ProxyConfigHolder) { adder.add(key, item) }
}