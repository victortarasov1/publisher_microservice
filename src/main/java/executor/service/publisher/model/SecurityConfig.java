package executor.service.publisher.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class SecurityConfig {
    @Value("${jwt.secret.key}")
    private String secretKey;

    public SecurityConfig(String secretKey) {
        this.secretKey = secretKey;
    }

    public SecurityConfig() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecurityConfig securityConfig)) return false;
        return Objects.equals(secretKey, securityConfig.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secretKey);
    }

    @Override
    public String toString() {
        return "SecurityConfigDto{" +
                "secretKey='" + secretKey + '\'' +
                '}';
    }
}
