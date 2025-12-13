package asembly.cart_service.client;

import asembly.dto.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-client", url = "${feign.service.product-service}")
public interface ProductClient {

    @GetMapping("/get")
    ResponseEntity<ProductResponse> getProductById(@RequestParam String id);
}
