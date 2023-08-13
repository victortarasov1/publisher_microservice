package executor.service.publisher.exception.source;

public class UnknownSourceServiceException extends SourceException {
    public UnknownSourceServiceException(String type) {
        super("unknown type of of source service: " + type);
    }
}
