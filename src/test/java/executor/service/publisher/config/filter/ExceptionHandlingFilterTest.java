package executor.service.publisher.config.filter;

import executor.service.publisher.exception.security.AuthorizationException;
import executor.service.publisher.exception.security.SecurityAuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExceptionHandlingFilterTest {
    private ExceptionHandlingFilter exceptionHandlingFilter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    public void setup() {
        exceptionHandlingFilter = new ExceptionHandlingFilter();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = mock(FilterChain.class);
    }

    @Test
    public void testDoFilterInternal_WhenNoException_ShouldPassThrough() throws ServletException, IOException {
        exceptionHandlingFilter.doFilterInternal(request, response, filterChain);
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }

    @Test
    public void testDoFilterInternal_WhenSecurityAuthException_ShouldHandleException() throws ServletException, IOException {
        SecurityAuthException exception = new AuthorizationException(new RuntimeException());
        doThrow(exception).when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));
        exceptionHandlingFilter.doFilterInternal(request, response, filterChain);
        assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatus());
        assertEquals(exception.getMessage(), response.getHeader(HttpHeaders.WWW_AUTHENTICATE));
    }

    @Test
    public void testDoFilterInternal_WhenOtherException_ShouldNotHandleException() throws ServletException, IOException {
        RuntimeException exception = new RuntimeException();
        doThrow(exception).when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));
        assertThrows(RuntimeException.class, () -> exceptionHandlingFilter.doFilterInternal(request, response, filterChain));
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }
}