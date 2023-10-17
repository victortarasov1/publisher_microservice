package executor.service.publisher.security.authorization;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import executor.service.publisher.security.TokenClaim;
import executor.service.publisher.exception.security.AuthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class BasicTokenAuthorization implements TokenBasedAuthorization {

    private final JWTVerifier verifier;

    @Override
    public void authorizeIfTokenValid(String jwtToken) {
        try {
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            String username = decodedJWT.getSubject();
            List<String> roles = Optional.ofNullable(decodedJWT.getClaim(TokenClaim.ROLES.getClaim()).asList(String.class)).orElse(Collections.emptyList());
            List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (TokenExpiredException | JWTDecodeException | SignatureVerificationException ex) {
            throw new AuthorizationException(ex);
        }
    }
}
