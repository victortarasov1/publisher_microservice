package executor.service.source.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ConnectionConfig {
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();

    }
}
