package executor.service.publisher.authorization;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import executor.service.publisher.exception.security.AuthorizationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BasicTokenAuthorizationTest {

    private JWTVerifier verifier;

    private DecodedJWT decodedJWT;

    private BasicTokenAuthorization tokenAuthorization;

    @BeforeEach
    public void setUp() {
        verifier = mock(JWTVerifier.class);
        decodedJWT = mock(DecodedJWT.class);
        when(verifier.verify(anyString())).thenReturn(decodedJWT);
        tokenAuthorization = new BasicTokenAuthorization(verifier);
    }

    @Test
    public void authorizeIfTokenValid_ShouldSetAuthenticationContext_WhenTokenIsValid() {
        String jwtToken = "valid-jwt-token";
        String username = "john.doe";
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
        when(verifier.verify(jwtToken)).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn(username);
        tokenAuthorization.authorizeIfTokenValid(jwtToken);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isEqualTo(authenticationToken);
    }

    @Test
    public void authorizeIfTokenValid_ShouldThrowAuthorizationException_WhenTokenIsInvalid() {
        String jwtToken = "invalid-jwt-token";
        when(verifier.verify(jwtToken)).thenThrow(SignatureVerificationException.class);
        assertThatThrownBy(() -> tokenAuthorization.authorizeIfTokenValid(jwtToken))
                .isInstanceOf(AuthorizationException.class)
                .hasCauseInstanceOf(SignatureVerificationException.class);
    }

    @Test
    public void authorizeIfTokenValid_ShouldThrowAuthorizationException_WhenTokenIsMalformed() {
        String jwtToken = "malformed-jwt-token";
        when(verifier.verify(jwtToken)).thenThrow(JWTDecodeException.class);
        assertThatThrownBy(() -> tokenAuthorization.authorizeIfTokenValid(jwtToken))
                .isInstanceOf(AuthorizationException.class)
                .hasCauseInstanceOf(JWTDecodeException.class);
    }

}