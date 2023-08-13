package executor.service.publisher.exception.validator;

public class UnknownProxyTypeException extends ValidationException {
    public UnknownProxyTypeException(String type) {
        super("unknown proxy type exception: " +  type);
    }
}
