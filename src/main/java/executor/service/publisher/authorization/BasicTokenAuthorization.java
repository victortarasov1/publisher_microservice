package executor.service.publisher.authorization;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import executor.service.publisher.exception.security.AuthorizationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class BasicTokenAuthorization implements TokenBasedAuthorization {

    private final JWTVerifier verifier;

    public BasicTokenAuthorization(JWTVerifier verifier) {
        this.verifier = verifier;
    }


    @Override
    public void authorizeIfTokenValid(String jwtToken) {
        try {
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            String username = decodedJWT.getSubject();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (JWTDecodeException | SignatureVerificationException ex) {
            throw new AuthorizationException(ex);
        }
    }
}
