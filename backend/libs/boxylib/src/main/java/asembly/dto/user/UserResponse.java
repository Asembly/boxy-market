package asembly.dto.user;

import asembly.type.Role;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
public record UserResponse(String id, String username, String password, Collection<Role> roles, LocalDateTime created_at){}
