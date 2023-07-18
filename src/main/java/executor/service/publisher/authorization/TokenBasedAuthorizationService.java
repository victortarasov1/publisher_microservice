package executor.service.publisher.authorization;

public interface TokenBasedAuthorizationService {
    void authorizeIfTokenValid(String jwtToken);
}
