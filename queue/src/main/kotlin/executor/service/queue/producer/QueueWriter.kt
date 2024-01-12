package executor.service.queue.producer

internal interface QueueWriter {
    fun <T> write(key: String, item: T)
}