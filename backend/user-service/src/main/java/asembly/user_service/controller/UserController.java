package asembly.user_service.controller;

import asembly.dto.user.UserCreateRequest;
import asembly.dto.user.UserResponse;
import asembly.dto.user.UserUpdateRequest;
import asembly.user_service.entity.User;
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

    @PostMapping("/")
    public ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest dto) {
        return userService.create(dto);
    }

    @GetMapping("/get/username")
    public ResponseEntity<UserResponse> getByUsername(@RequestParam String username)
    {
        return userService.findByUsername(username);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll()
    {
        return userService.findAll();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getById(@RequestParam String id)
    {
        return userService.findById(id);
    }

//    @PostMapping("/byIds")
//    public ResponseEntity<List<UserResponse>> findAllByIds(@RequestBody UserIdsRequest dto)
//    {
//       return userService.findAllByIds(dto);
//    }

    @PatchMapping("/")
    public ResponseEntity<User> update(@RequestParam String id,@RequestBody UserUpdateRequest dto)
    {
       return userService.update(id, dto);
    }

//    @DeleteMapping("/")
//    public ResponseEntity<String> deleteAll()
//    {
//        return userService.deleteAll();
//    }

    @DeleteMapping("/")
    public ResponseEntity<User> delete(@RequestParam String id)
    {
        return userService.delete(id);
    }

}
