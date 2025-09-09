package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.UserCreationRequest;
import com.trinhquangminh.webdulich.dto.response.UserResponse;
import com.trinhquangminh.webdulich.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    Users toUser(UserCreationRequest request);

    UserResponse toUserResponse(Users user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateUser(@MappingTarget Users user, UserCreationRequest request);
}
