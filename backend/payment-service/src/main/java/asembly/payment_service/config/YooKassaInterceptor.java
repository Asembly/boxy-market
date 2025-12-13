package asembly.payment_service.config;

import asembly.utils.GeneratorId;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
public class YooKassaInterceptor implements RequestInterceptor {

    @Value("${feign.service.yookassa.shop-id}")
    private String shopId;

    @Value("${feign.service.yookassa.secret}")
    private String secretKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        String auth = shopId + ":" + secretKey;
        String encodedAuth = Base64
                .getEncoder()
                .encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        requestTemplate.header("Authorization", "Basic " + encodedAuth);

        if(requestTemplate.method().contains("POST") && requestTemplate.path().contains("/payments"))
        {
            var indempotenceKey = GeneratorId.generateId();
            requestTemplate.header("Idempotence-Key", indempotenceKey);
        }

        requestTemplate.header("Content-Type", "application/json");
    }
}
