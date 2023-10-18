package executor.service.source.exception;

public class SourceException extends RuntimeException {
    public SourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceException(String message) {
        super(message);
    }
}
