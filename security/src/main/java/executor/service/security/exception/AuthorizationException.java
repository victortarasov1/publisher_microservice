package executor.service.security.exception;

public class AuthorizationException extends SecurityAuthException {
    public AuthorizationException(Throwable cause) {
        super("authorization failed", cause);
    }
}
