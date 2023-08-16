package executor.service.publisher.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class SecurityConfigDto {
    @Value("${jwt.secret.key}")
    private String secretKey;

    public SecurityConfigDto(String secretKey) {
        this.secretKey = secretKey;
    }

    public SecurityConfigDto() {
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
        if (!(o instanceof SecurityConfigDto securityConfigDto)) return false;
        return Objects.equals(secretKey, securityConfigDto.secretKey);
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
