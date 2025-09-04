package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.UserCreationRequest;
import com.trinhquangminh.webdulich.dto.response.UserResponse;
import com.trinhquangminh.webdulich.mapper.user.UserMapper;
import com.trinhquangminh.webdulich.model.Users;
import com.trinhquangminh.webdulich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request){
        if (userRepository.existsByName(request.getName())){
            throw new RuntimeException("Username already exists");
        }
        Users users = userMapper.toUser(request);
        users = userRepository.save(users);
        return userMapper.toUserResponse(users);
    }

    public List<UserResponse> getAllUsers(){
        List<Users> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }
}
