package executor.service.publisher.config.filter;

import executor.service.publisher.authorization.TokenBasedAuthorization;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

class AuthorizationFilterTest {
    public static final String TOKEN = "some-token";
    private AuthorizationFilter authorizationFilter;
    private TokenBasedAuthorization tokenBasedAuthorization;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;


    @BeforeEach
    public void setup() {
        tokenBasedAuthorization = mock(TokenBasedAuthorization.class);
        authorizationFilter = new AuthorizationFilter(tokenBasedAuthorization);
        request = mock(HttpServletRequest.class);
        response = new MockHttpServletResponse();
        filterChain = mock(FilterChain.class);
    }
    @Test
    public void testDoFilterInternal_WhenBearerTokenPresent_ShouldCallTokenBasedAuthorization() throws ServletException, IOException {
        when(request.getHeader(AUTHORIZATION)).thenReturn(AuthorizationType.BEARER.getPrefix() + TOKEN);
        authorizationFilter.doFilterInternal(request, response, filterChain);
        verify(tokenBasedAuthorization, times(1)).authorizeIfTokenValid(TOKEN);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_WhenNonBearerTokenPresent_ShouldNotCallTokenBasedAuthorization() throws ServletException, IOException {
        when(request.getHeader(AUTHORIZATION)).thenReturn(TOKEN);
        authorizationFilter.doFilterInternal(request, response, filterChain);
        verify(tokenBasedAuthorization, times(0)).authorizeIfTokenValid(anyString());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_WhenTokenAbsent_ShouldNotCallTokenBasedAuthorization() throws ServletException, IOException {
        when(request.getHeader(AUTHORIZATION)).thenReturn(null);
        authorizationFilter.doFilterInternal(request, response, filterChain);
        verify(tokenBasedAuthorization, times(0)).authorizeIfTokenValid(anyString());
        verify(filterChain, times(1)).doFilter(request, response);
    }



}