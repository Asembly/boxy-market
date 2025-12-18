package asembly.user_service.service;

import asembly.dto.user.UserCreateRequest;
import asembly.dto.user.UserResponse;
import asembly.dto.user.UserUpdateRequest;
import asembly.event.types.UserEventType;
import asembly.exception.user.UserAlreadyExistException;
import asembly.exception.user.UserNotFoundException;
import asembly.type.Role;
import asembly.user_service.entity.User;
import asembly.user_service.kafka.ProducerUser;
import asembly.user_service.mapper.UserMapper;
import asembly.user_service.repository.UserRepository;
import asembly.utils.GeneratorId;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProducerUser producerService;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public ResponseEntity<List<UserResponse>> getUsers()
    {
        var users = userRepository.findAll();

        var usersResponse = users.stream().map(userMapper::userToUserResponse).toList();
        return ResponseEntity.ok(usersResponse);
    }

    public ResponseEntity<UserResponse> getUserById(String id)
    {
        var user = userRepository.findById(id).orElseThrow(
                UserNotFoundException::new
        );

        var userResponse = userMapper.userToUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<UserResponse> deleteUser(String id)
    {
        var user = userRepository.findById(id).orElseThrow(
                UserNotFoundException::new
        );

        producerService.sendEvent(
                UserEventType.USER_DELETED,
                id
        );

        userRepository.delete(user);

        var userResponse = userMapper.userToUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<UserResponse> getUserByUsername(String username)
    {
        var user = userRepository.findByUsername(username).orElseThrow(
                UserNotFoundException::new
        );
        return ResponseEntity.ok(userMapper.userToUserResponse(user));
    }

    public ResponseEntity<UserResponse> createUser(UserCreateRequest dto) {

        var optUser = userRepository.findByUsername(dto.username());

        if(optUser.isPresent())
            throw new UserAlreadyExistException("User already exist in database");

        if(dto.username().isEmpty())
            throw new ValidationException("Username is empty");

        if(dto.password().isEmpty())
            throw new ValidationException("Password is empty");

        var user = new User(
                GeneratorId.generateId(),
                dto.username(),
                dto.password(),
                List.of(Role.ROLE_USER),
                LocalDateTime.now());

        log.info("User entity: {}",user);

        producerService.sendEvent(
                UserEventType.USER_CREATED,
                user.getId()
        );

        var save = userRepository.save(user);
        var userResponse = userMapper.userToUserResponse(save);

        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<UserResponse> updateUser(String id, UserUpdateRequest dto)
    {
        var isChange = false;
        var user = userRepository.findById(id).orElseThrow(
                UserNotFoundException::new
        );

        if(!dto.username().isEmpty())
        {
            if(userRepository.findByUsername(dto.username()).isPresent())
                throw new UserAlreadyExistException("User with username: " + dto.username() + " already exist");
            else
            {
                user.setUsername(dto.username());
                isChange = true;
            }
        }

        if(!dto.password().isEmpty())
        {
            user.setPassword(dto.password());
            isChange = true;
        }

        var save = isChange ? userRepository.save(user) : user;
        var userResponse = userMapper.userToUserResponse(save);

        return ResponseEntity.ok(userResponse);
    }
}
