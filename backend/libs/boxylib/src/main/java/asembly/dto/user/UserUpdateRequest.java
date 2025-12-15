package asembly.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserUpdateRequest(
        @NotBlank(message = "Username required")
        @Size(min = 3, max = 50, message = "Username must be from 3 to 50 symbols")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Имя пользователя может содержать только буквы, цифры и подчеркивание")
        String username,

        @NotBlank(message = "Password required")
        @Size(min = 8, message = "Password must be from 8 symbols")
        String password
) {}
