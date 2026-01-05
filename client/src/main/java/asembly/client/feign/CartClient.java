package asembly.client.feign;

import asembly.dto.product.ProductCreateDto;
import asembly.dto.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "product-client", url = "${feign.services}/product-service")
public interface ProductClient {

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getProducts();
}
