package executor.service.publisher.exception.security;

public class SecurityAuthException extends RuntimeException {
    public SecurityAuthException(String message){
        super(message);
    }
    public SecurityAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
