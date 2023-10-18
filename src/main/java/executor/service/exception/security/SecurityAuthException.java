package executor.service.exception.security;

public class SecurityAuthException extends RuntimeException {
    public SecurityAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
