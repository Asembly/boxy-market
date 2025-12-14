package asembly.dto.user;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(String id, String username, String password, LocalDateTime created_at){}
