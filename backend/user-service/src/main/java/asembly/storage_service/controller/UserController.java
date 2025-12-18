package asembly.user_service.controller;

import asembly.dto.user.UserCreateRequest;
import asembly.dto.user.UserResponse;
import asembly.dto.user.UserUpdateRequest;
import asembly.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-service")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("/get")
    public ResponseEntity<UserResponse> getUserById(@RequestParam String id)
    {
        return userService.getUserById(id);
    }

    @GetMapping("/get/username")
    public ResponseEntity<UserResponse> getUserByUsername(@RequestParam String username) { return userService.getUserByUsername(username); }

    @PostMapping("/")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest dto) {
        return userService.createUser(dto);
    }

    @PatchMapping("/")
    public ResponseEntity<UserResponse> updateUser(@RequestParam String id,@RequestBody UserUpdateRequest dto)
    {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/")
    public ResponseEntity<UserResponse> deleteUser(@RequestParam String id)
    {
        return userService.deleteUser(id);
    }

}
