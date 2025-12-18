package asembly.storage_service.service;

import asembly.dto.user.UserCreateRequest;
import asembly.dto.user.UserResponse;
import asembly.dto.user.UserUpdateRequest;
import asembly.exception.user.UserAlreadyExistException;
import asembly.exception.user.UserNotFoundException;
import asembly.storage_service.entity.User;
import asembly.storage_service.kafka.ProducerUser;
import asembly.storage_service.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProducerUser producerUser;

    @InjectMocks
    private UserService userService;

    private UserCreateRequest validUser;
    private User savedUser;

    @BeforeEach
    public void setup() {
        validUser = UserCreateRequest.builder()
                .username("test")
                .password("111111111")
                .build();

        savedUser = User.builder()
                .id("test-id-generate")
                .username("test")
                .password("111111111")
                .created_at(LocalDateTime.now())
                .build();
    }

    @Test
    public void createUser_userAlreadyExist_throwException()
    {
        var existUser = User.builder()
                .id("test-id-generate")
                .username("test")
                .created_at(LocalDateTime.now())
                .build();

        when(userRepository.findByUsername("test"))
                .thenReturn(Optional.of(existUser));

        UserAlreadyExistException exception = assertThrows(
                UserAlreadyExistException.class,
                () -> userService.createUser(validUser)
        );

        assertNotNull(exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void createUser_withEmptyUsername_throwException()
    {
        var userWithoutUsername = UserCreateRequest.builder()
                .username("")
                .password("123123123")
                .build();

        when(userRepository.findByUsername("")).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> userService.createUser(userWithoutUsername)
        );

        assertNotNull(exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void createUser_withEmptyPassword_throwException()
    {
        var userWithoutUsername = UserCreateRequest.builder()
                .username("testUsername")
                .password("")
                .build();

        when(userRepository.findByUsername("testUsername")).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> userService.createUser(userWithoutUsername)
        );

        assertNotNull(exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void createUser_withValidRequest_returnResponseEntityUserResponse()
    {
        when(userRepository.findByUsername("test"))
                .thenReturn(Optional.empty());

        when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(savedUser);

        ResponseEntity<UserResponse> response = userService.createUser(validUser);

        assertNotNull(response);
        assertEquals(200,response.getStatusCode().value());
        assertNotNull(response.getBody());

        UserResponse body = response.getBody();

        assertEquals("test-id-generate", body.id());
        assertEquals("test", body.username());

        verify(userRepository, times(1)).findByUsername("test");
    }

    @Test
    public void updateUser_withValidRequest_returnResponseEntityUserResponse()
    {

        var newUser = UserUpdateRequest.builder()
                .username("tester")
                .password("123321123")
                .build();

        when(userRepository.findById("test-id-generate"))
                .thenReturn(Optional.of(savedUser));

        when(userRepository.findByUsername("tester"))
                .thenReturn(Optional.empty());

        when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(savedUser);

        ResponseEntity<UserResponse> response = userService.updateUser("test-id-generate", newUser);

        log.info("Response body: {}",response.getBody());

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        var body = response.getBody();

        assertEquals("tester", body.username());

        verify(userRepository, times(1)).findByUsername("tester");
        verify(userRepository, times(1)).findById("test-id-generate");
    }

    @Test
    public void updateUser_withNotValidId_throwUserNotFoundException()
    {
        var newUser = UserUpdateRequest.builder()
                .username("tester")
                .password("123321123")
                .build();

        when(userRepository.findById("test-not_valid_id-generate"))
                .thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> userService.updateUser("test-not_valid_id-generate", newUser)
        );

        assertNotNull(exception.getMessage());

        verify(userRepository, never()).findByUsername("test-not_valid_id-generate");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void updateUser_withExistUsername_throwUserAlreadyExistException()
    {
        var newUser = UserUpdateRequest.builder()
                .username("tester")
                .password("123321123")
                .build();

        var existUser = User.builder()
                .id("test-id2-generate")
                .username("tester")
                .password("123123123")
                .created_at(LocalDateTime.now())
                .build();

        when(userRepository.findById("test-id-generate"))
                .thenReturn(Optional.of(savedUser));

        when(userRepository.findByUsername("tester"))
                .thenReturn(Optional.of(existUser));

        UserAlreadyExistException exception = assertThrows(
                UserAlreadyExistException.class,
                () -> userService.updateUser("test-id-generate", newUser)
        );

        log.info("Exception: {}",exception.getMessage());

        assertNotNull(exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }

}
