package asembly.user_service.service;

import asembly.dto.user.UserCreateRequest;
import asembly.dto.user.UserResponse;
import asembly.exception.user.UserAlreadyExistException;
import asembly.user_service.entity.User;
import asembly.user_service.kafka.ProducerUser;
import asembly.user_service.repository.UserRepository;
import jakarta.validation.ValidationException;
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

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProducerUser producerUser;

    @InjectMocks
    private UserService userService;

    private UserCreateRequest validUser;
    private UserResponse expectedUserResponse;
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

        expectedUserResponse = UserResponse.builder()
                .id("test-id-generate")
                .username("test")
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
    public void createUser_withValidRequest_returnUserResponse()
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
}
