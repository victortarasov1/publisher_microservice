package executor.service.security.exception;

public class SecurityAuthException extends RuntimeException {
    public SecurityAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
