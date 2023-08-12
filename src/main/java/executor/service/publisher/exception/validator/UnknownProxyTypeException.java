package executor.service.publisher.exception.validator;

public class UnknownProxyTypeException extends RuntimeException {
    public UnknownProxyTypeException(String type) {
        super("unknown proxy type exception");
    }
}
