package executor.service.queue.producer

interface QueueWriter {
    fun <T> write(key: String, item: T)
}