package executor.service.queue.producer

interface QueueProducer<T> {
    fun add(item: T)
}