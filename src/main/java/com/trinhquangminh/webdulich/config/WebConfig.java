package com.trinhquangminh.webdulich.config;

import com.trinhquangminh.webdulich.model.Users;
import com.trinhquangminh.webdulich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class WebConfig {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            if (userRepository.findByName("admin").isEmpty()){
                var user = Users.builder()
                        .name("admin")
                        .password(passwordEncoder.encode("admin"))
                        .email("admin@example.com")
                        .phone("0354883247")
                        .address("Hanoi, Vietnam")
                        .role(Users.ROLE_ADMIN)
                        .build();
                userRepository.save(user);
                log.info("Admin user created with name: {}", user.getName());
            }
        };
    }
}
