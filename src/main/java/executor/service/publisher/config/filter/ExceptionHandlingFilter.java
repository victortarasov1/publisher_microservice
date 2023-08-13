package executor.service.publisher.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.publisher.dto.ApiError;
import executor.service.publisher.exception.security.SecurityAuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
@Order(1)
public class ExceptionHandlingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (SecurityAuthException ex) {
            response.setHeader(HttpHeaders.WWW_AUTHENTICATE, ex.getMessage());
            response.setStatus(UNAUTHORIZED.value());
            List<String> debugMessages = ex.getCause() != null ? List.of(ex.getCause().getMessage()) : List.of();
            ApiError error = new ApiError(ex.getMessage(), debugMessages);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }
}
