package executor.service.publisher.authorization;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import executor.service.publisher.security.TokenClaim;
import executor.service.publisher.exception.security.AuthorizationException;
import executor.service.publisher.security.authorization.BasicTokenAuthorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BasicTokenAuthorizationTest {

    public static final String USERNAME = "worker";
    public static final String ROLE = "ROLE_USER";
    public static final String JWT_TOKEN = "some-jwt-token";
    private JWTVerifier verifier;
    private DecodedJWT decodedJWT;
    private BasicTokenAuthorization tokenAuthorization;
    private Claim claim;

    @BeforeEach
    public void setUp() {
        verifier = mock(JWTVerifier.class);
        decodedJWT = mock(DecodedJWT.class);
        claim = mock(Claim.class);
        when(verifier.verify(anyString())).thenReturn(decodedJWT);
        tokenAuthorization = new BasicTokenAuthorization(verifier);
    }

    @Test
    public void testAuthorizeIfTokenValid_ShouldSetAuthenticationContext_WhenTokenIsValid() {
        List<String> roles = List.of(ROLE);
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(ROLE));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(USERNAME, null, authorities);
        when(decodedJWT.getSubject()).thenReturn(USERNAME);
        when(decodedJWT.getClaim(TokenClaim.ROLES.getClaim())).thenReturn(claim);
        when(claim.asList(String.class)).thenReturn(roles);
        tokenAuthorization.authorizeIfTokenValid(JWT_TOKEN);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isEqualTo(authenticationToken);
    }
    @Test
    public void testAuthorizeIfTokenPayloadIsEmpty_ShouldSetAuthenticationContextToNull() {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(null, null, null);
        when(decodedJWT.getSubject()).thenReturn(null);
        when(decodedJWT.getClaim(TokenClaim.ROLES.getClaim())).thenReturn(claim);
        when(claim.asList(String.class)).thenReturn(null);
        tokenAuthorization.authorizeIfTokenValid(JWT_TOKEN);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isEqualTo(authenticationToken);
    }
    @Test
    public void testAuthorizeIfTokenValid_ShouldThrowAuthorizationException_WhenTokenIsInvalid() {
        when(verifier.verify(JWT_TOKEN)).thenThrow(SignatureVerificationException.class);
        assertThatThrownBy(() -> tokenAuthorization.authorizeIfTokenValid(JWT_TOKEN))
                .isInstanceOf(AuthorizationException.class)
                .hasCauseInstanceOf(SignatureVerificationException.class);
    }

    @Test
    public void testAuthorizeIfTokenValid_ShouldThrowAuthorizationException_WhenTokenIsMalformed() {
        when(verifier.verify(JWT_TOKEN)).thenThrow(JWTDecodeException.class);
        assertThatThrownBy(() -> tokenAuthorization.authorizeIfTokenValid(JWT_TOKEN))
                .isInstanceOf(AuthorizationException.class)
                .hasCauseInstanceOf(JWTDecodeException.class);
    }

    @Test
    public void testAuthorizeIfTokenValid_ShouldThrowAuthorizationException_WhenTokenIsExpired() {
        when(verifier.verify(JWT_TOKEN)).thenThrow(TokenExpiredException.class);
        assertThatThrownBy(() -> tokenAuthorization.authorizeIfTokenValid(JWT_TOKEN))
                .isInstanceOf(AuthorizationException.class)
                .hasCauseInstanceOf(TokenExpiredException.class);
    }
}