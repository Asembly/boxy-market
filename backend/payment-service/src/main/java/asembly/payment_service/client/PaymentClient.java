package asembly.payment_service.client;

import asembly.dto.CreatePaymentDto;
import asembly.dto.PaymentResponseDto;
import asembly.payment_service.config.YooKassaFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "yookassa-client", url = "${feign.service.yookassa.url}",
        configuration = YooKassaFeignConfig.class
)
public interface PaymentClient {

    @PostMapping("/payments")
    public PaymentResponseDto createPayment(
            @RequestBody CreatePaymentDto dto
    );


}
