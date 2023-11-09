package executor.service.queue.producer.proxy

import executor.service.model.ProxyConfigHolder
import executor.service.queue.producer.QueueProducer

interface ProxyQueueProducer: QueueProducer<ProxyConfigHolder>