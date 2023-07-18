package executor.service.publisher.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import executor.service.publisher.authorization.BasicTokenAuthorization;
import executor.service.publisher.authorization.TokenBasedAuthorization;
import executor.service.publisher.config.filter.AuthorizationFilter;
import executor.service.publisher.config.filter.ExceptionHandlingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final ExceptionHandlingFilter exceptionHandlingFilter;
    @Value("${inmemory.user.name}")
    private String NAME;

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    public SecurityConfig(ExceptionHandlingFilter exceptionHandlingFilter) {
        this.exceptionHandlingFilter = exceptionHandlingFilter;
    }
    @Bean
    public JWTVerifier verifier() {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        return JWT.require(algorithm).build();
    }

    @Bean
    public TokenBasedAuthorization tokenBasedAuthorization(JWTVerifier verifier) {
        return new BasicTokenAuthorization(verifier);
    }

    @Bean
    public AuthorizationFilter authorizationFilter(TokenBasedAuthorization tokenBasedAuthorization) {
        return new AuthorizationFilter(tokenBasedAuthorization);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationFilter authorizationFilter) throws Exception {
        http
                .addFilterBefore(authorizationFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlingFilter, AuthorizationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        UserDetails user = User.builder()
                .username(NAME)
                .password("")
                .roles()
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
