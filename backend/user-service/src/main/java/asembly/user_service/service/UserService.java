package asembly.user_service.service;

import asembly.dto.user.UserCreateRequest;
import asembly.dto.user.UserResponse;
import asembly.dto.user.UserUpdateRequest;
import asembly.event.types.UserEventType;
import asembly.exception.user.UserAlreadyExistException;
import asembly.exception.user.UserNotFoundException;
import asembly.user_service.entity.User;
import asembly.user_service.kafka.ProducerUser;
import asembly.user_service.mapper.UserMapper;
import asembly.user_service.repository.UserRepository;
import asembly.utils.GeneratorId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProducerUser producerService;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public ResponseEntity<List<User>> findAll()
    {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<?> findById(String id)
    {
        var user = userRepository.findById(id).orElseThrow(
                UserNotFoundException::new
        );
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<String> deleteAll()
    {
        userRepository.deleteAll();
        return ResponseEntity.ok("Users deleted");
    }

    public ResponseEntity<User> delete(String id)
    {
        var user = userRepository.findById(id).orElseThrow(
                UserNotFoundException::new
        );

        producerService.sendEvent(
                UserEventType.USER_DELETED,
                id
        );

        userRepository.delete(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<UserResponse> findByUsername(String username)
    {
        var user = userRepository.findByUsername(username).orElseThrow(
                UserNotFoundException::new
        );
        return ResponseEntity.ok(userMapper.userToUserResponse(user));
    }

//    public ResponseEntity<List<UserResponse>> findAllByIds(UserIdsRequest dto)
//    {
//        var users = userRepository.findAllById(dto.ids());
//        if(users.isEmpty())
//            throw new UserNotFoundException("Users not found in database");
//
//        var usersResponse = userMapper.usersToUserResponse(users);
//
//        log.info("All users: {}", usersResponse);
//
//        return ResponseEntity.ok(usersResponse);
//    }

    public ResponseEntity<UserResponse> create(UserCreateRequest dto) {

        var optUser = userRepository.findByUsername(dto.username());

        if(optUser.isPresent())
            throw new UserAlreadyExistException("User already exist in database.");

        var user = new User(
                GeneratorId.generateId(),
                dto.username(),
                dto.password(),
                LocalDate.now());

        producerService.sendEvent(
                UserEventType.USER_CREATED,
                user.getId()
        );

        return ResponseEntity.ok(userMapper.userToUserResponse(userRepository.save(user)));
    }

    public ResponseEntity<User> update(String id, UserUpdateRequest dto)
    {
        var user = userRepository.findById(id).orElseThrow();

        if(!dto.username().isEmpty())
        {
            if(userRepository.findByUsername(dto.username()).isPresent())
                return ResponseEntity.badRequest().build();
            else
                user.setUsername(dto.username());
        }

        if(!dto.password().isEmpty())
            user.setPassword(dto.password());

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}
