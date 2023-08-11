package executor.service.publisher.exception.source;

public class SourceException extends RuntimeException {
    public SourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceException(String message) {
        super(message);
    }
}
