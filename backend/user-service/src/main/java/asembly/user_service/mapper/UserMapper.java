package asembly.user_service.mapper;

import asembly.dto.user.UserResponse;
import asembly.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);
    List<UserResponse> usersToUserResponse(List<User> users);

}
