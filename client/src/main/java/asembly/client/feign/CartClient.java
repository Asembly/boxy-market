package asembly.client.feign;

import asembly.dto.cart.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cart-client", url = "${feign.services}/cart-service")
public interface CartClient {

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addProduct(@RequestParam String user_id, @RequestParam String product_id);

    @GetMapping("/get/user")
    public ResponseEntity<CartResponse> getCartByUserId(@RequestParam String user_id);


    @PatchMapping("/remove")
    public ResponseEntity<CartResponse> removeProduct(@RequestParam String user_id, @RequestParam String product_id);

}
