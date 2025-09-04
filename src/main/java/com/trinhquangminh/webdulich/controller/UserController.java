package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.UserCreationRequest;
import com.trinhquangminh.webdulich.dto.response.UserResponse;
import com.trinhquangminh.webdulich.service.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserCreationRequest request){
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
}
