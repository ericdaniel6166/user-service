package com.example.userservice.api;

import com.example.springbootmicroservicesframework.dto.MessageResponse;
import com.example.springbootmicroservicesframework.exception.ValidationException;
import com.example.springbootmicroservicesframework.utils.UriConst;
import com.example.userservice.dto.AuthenticationResponse;
import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.RegisterAccountRequest;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Validated
@RequestMapping(UriConst.AUTH)
public class AuthApi {
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody @Valid RegisterAccountRequest request) throws ValidationException {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping(UriConst.VERIFY_TOKEN)
    public ResponseEntity<MessageResponse> verifyToken() {
        return ResponseEntity.ok(userService.verifyToken());
    }


}
