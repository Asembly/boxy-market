package asembly.auth_service.client;

import asembly.auth_service.config.FeignConfig;
import asembly.dto.user.UserCreateRequest;
import asembly.dto.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-client", url = "${feign.service.user-service}", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/get/username")
    ResponseEntity<UserResponse> getUserByUsername(@RequestParam String username);

    @GetMapping("/get")
    ResponseEntity<UserResponse> getUserById(@RequestParam String id);

    @PostMapping("/")
    ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest dto);

}