package asembly.product_service.client;

import asembly.dto.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-client", url = "${feign.service.user-service}")
public interface UserClient {

    @GetMapping("/get")
    public ResponseEntity<UserResponse> getUserById(@RequestParam String id);
}
