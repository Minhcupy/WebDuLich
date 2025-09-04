package com.trinhquangminh.webdulich.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserCreationRequest {
    String name;
    String password;
    String email;
    Integer phone;
    String address;
    String role;
}
