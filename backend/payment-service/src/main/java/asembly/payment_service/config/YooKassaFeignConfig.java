package asembly.payment_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YooKassaFeignConfig {

    @Bean
    public YooKassaInterceptor yooKassaAuthInterceptor() {
        return new YooKassaInterceptor();
    }

}
