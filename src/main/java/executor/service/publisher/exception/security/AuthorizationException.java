package executor.service.publisher.exception.security;

public class AuthorizationException extends SecurityAuthException {
    public AuthorizationException(Throwable cause) {
        super("authorization failed", cause);
    }
}
