package executor.service.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.ApiError;
import executor.service.security.exception.SecurityAuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
@Order(1)
@RequiredArgsConstructor
public class ExceptionHandlingFilter extends OncePerRequestFilter {
    private final ObjectMapper mapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (SecurityAuthException ex) {
            response.setHeader(HttpHeaders.WWW_AUTHENTICATE, ex.getMessage());
            response.setStatus(UNAUTHORIZED.value());
            List<String> debugMessages = Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).stream().toList();
            ApiError error = new ApiError(ex.getMessage(), debugMessages);
            response.setContentType(APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getOutputStream(), error);
        }
    }
}
