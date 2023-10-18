package executor.service.exception.source;

public class DataParsingException extends SourceException {
    public DataParsingException(Throwable cause) {
        super("data parsion exception", cause);
    }
}
