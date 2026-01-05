package asembly.client.feign;

import asembly.dto.auth.AuthRequest;
import asembly.dto.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "auth-client", url = "${feign.services}/auth-service")
public interface AuthClient {

    @PostMapping("/sign-up")
    public ResponseEntity<?>signUp(@RequestBody AuthRequest user);
}
