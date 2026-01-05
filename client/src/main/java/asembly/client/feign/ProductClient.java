package asembly.client.feign;

import asembly.dto.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-client", url = "${feign.services}/user-service")
public interface UserClient {

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getUsers();
}
