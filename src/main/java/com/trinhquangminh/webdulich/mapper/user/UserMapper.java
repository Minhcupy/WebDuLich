package com.trinhquangminh.webdulich.mapper.user;

import com.trinhquangminh.webdulich.dto.request.UserCreationRequest;
import com.trinhquangminh.webdulich.dto.response.UserResponse;
import com.trinhquangminh.webdulich.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toUser(UserCreationRequest request);
    UserResponse toUserResponse(Users user);
    void updateUser(@MappingTarget Users user, UserCreationRequest request);
}
