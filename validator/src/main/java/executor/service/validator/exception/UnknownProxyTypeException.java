package executor.service.validator.exception;

public class UnknownProxyTypeException extends ValidationException {
    public UnknownProxyTypeException(String type) {
        super("unknown proxy type exception: " +  type);
    }
}
