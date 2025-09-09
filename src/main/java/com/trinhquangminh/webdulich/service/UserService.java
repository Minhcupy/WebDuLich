package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.UserCreationRequest;
import com.trinhquangminh.webdulich.dto.response.UserResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.user.UserMapper;
import com.trinhquangminh.webdulich.model.Users;
import com.trinhquangminh.webdulich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
        if (userRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Users users = userMapper.toUser(request);
        users.setPassword(passwordEncoder.encode(request.getPassword()));

        users.setRole(Users.ROLE_USER);

        users = userRepository.save(users);
        return userMapper.toUserResponse(users);
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Users users = userRepository.findByName(name)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserResponse> getAllUsers(){
        List<Users> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @PreAuthorize("hasAuthority('ADMIN') or #id == principal.id")
    public UserResponse getUser(String id){
        Users users = userRepository.findById(Integer.parseInt(id))
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(users);
    }

    @PostAuthorize("returnObject.id == principal.id or hasAuthority('ADMIN')")
    public UserResponse updateUser(String id, UserCreationRequest request){
        Users users = userRepository.findById(Integer.parseInt(id))
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(users, request);
        users = userRepository.save(users);
        return userMapper.toUserResponse(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(String id){
        userRepository.deleteById(Integer.parseInt(id));
    }
}
