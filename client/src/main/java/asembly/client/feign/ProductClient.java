package asembly.client.feign;

import asembly.dto.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-client", url = "${feign.services}/product-service")
public interface ProductClient {

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getProducts();

    @GetMapping("/get")
    public ResponseEntity<ProductResponse> getProductById(@RequestParam String id);
}
