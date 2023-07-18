package executor.service.publisher.authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import executor.service.publisher.exception.security.AuthorizationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class BasicTokenAuthorizationService implements TokenBasedAuthorizationService {
    private final Algorithm algorithm;

    public BasicTokenAuthorizationService(@Value("${jwt.secret.key}") String SECRET_KEY) {
        this.algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
    }

    @Override
    public void authorizeIfTokenValid(String jwtToken) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            String username = decodedJWT.getSubject();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (TokenExpiredException | JWTDecodeException | SignatureVerificationException ex) {
            throw new AuthorizationException(ex);
        }
    }
}
