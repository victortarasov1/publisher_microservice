package executor.service.queue.producer

interface QueueAdder {
    fun <T> add(key: String, item: T)
}