package executor.service.publisher.authorization;

public interface TokenBasedAuthorization {
    void authorizeIfTokenValid(String jwtToken);
}
