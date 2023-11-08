package executor.service.source.exception;

public class DataParsingException extends SourceException {
    public DataParsingException(Throwable cause) {
        super("data parsion exception", cause);
    }
}
