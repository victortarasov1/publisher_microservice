package executor.service.publisher.authorization;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import executor.service.publisher.enums.TokenClaim;
import executor.service.publisher.exception.security.AuthorizationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Component
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
            List<String> roles = Optional.ofNullable(decodedJWT.getClaim(TokenClaim.ROLES.getClaim()).asList(String.class)).orElse(Collections.emptyList());
            List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
            String credentials = decodedJWT.getClaim(TokenClaim.CREDENTIALS.getClaim()).asString();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, credentials, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (TokenExpiredException | JWTDecodeException | SignatureVerificationException ex) {
            throw new AuthorizationException(ex);
        }
    }
}
