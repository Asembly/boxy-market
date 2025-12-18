package asembly.auth_service.controller;


import asembly.auth_service.service.AuthService;
import asembly.auth_service.service.RefreshService;
import asembly.dto.auth.AuthRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("auth-service")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RefreshService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity<?> updateAccessToken(@RequestParam String token){
        return refreshTokenService.updateAccessToken(token);
    }

    @GetMapping("/")
    public String testMethod()
    {
        log.info("TEST");
        return "This content only for admin";
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String token)
    {
        return refreshTokenService.logout(token);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?>signUp(@RequestBody AuthRequest user){
        return authService.signUp(user);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest userDto){
        return authService.signIn(userDto);
    }

}
