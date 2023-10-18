package executor.service.exception.source.okhttp;

import executor.service.exception.source.SourceException;

public class OkhttpException extends SourceException {
    public OkhttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public OkhttpException(String message) {
        super(message);
    }
}
