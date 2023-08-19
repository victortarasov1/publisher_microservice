package executor.service.publisher.config.filter;

import executor.service.publisher.authorization.TokenBasedAuthorization;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Component
@Order(2)
public class AuthorizationFilter extends OncePerRequestFilter {
    private final TokenBasedAuthorization tokenBasedAuthorization;

    public AuthorizationFilter(TokenBasedAuthorization tokenBasedAuthorization) {
        this.tokenBasedAuthorization = tokenBasedAuthorization;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION);
        if (header != null && header.startsWith(AuthorizationType.BEARER.getPrefix()))
            tokenBasedAuthorization.authorizeIfTokenValid(header.substring(AuthorizationType.BEARER.getPrefix().length()));
        filterChain.doFilter(request, response);
    }
}
