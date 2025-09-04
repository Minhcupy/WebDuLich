package com.trinhquangminh.webdulich.mapper.user;

import com.trinhquangminh.webdulich.dto.request.UserCreationRequest;
import com.trinhquangminh.webdulich.dto.response.UserResponse;
import com.trinhquangminh.webdulich.model.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //comment
    Users toUser(UserCreationRequest request);
    UserResponse toUserResponse(Users user);
}
