package com.example.userservice.api;

import com.example.userservice.dto.CreateUserRequest;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@Validated
public class UserApi {
    final UserService userService;

//    final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody @Valid CreateUserRequest request) {
        return userService.register(request);
    }


}
