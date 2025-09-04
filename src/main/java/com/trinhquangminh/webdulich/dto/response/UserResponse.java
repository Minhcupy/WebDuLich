package com.trinhquangminh.webdulich.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserResponse {
    Integer id;
    String name;
    String password;
    String email;
    Integer phone;
    String address;
    String role;
}
