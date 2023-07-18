package executor.service.publisher.exception.security;

public class AuthorizationException extends SecurityAuthException {
    public AuthorizationException() {
        super("authorization failed");
    }

    public AuthorizationException(Throwable cause) {
        super("authorization failed", cause);
    }
}
