package com.example.userservice.service;

import com.example.springbootmicroservicesframework.dto.MessageResponse;
import com.example.springbootmicroservicesframework.exception.ValidationException;
import com.example.userservice.dto.AuthResponse;
import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.RegisterAccountRequest;

public interface UserService {

    MessageResponse register(RegisterAccountRequest request) throws ValidationException;

    AuthResponse login(LoginRequest request);

    MessageResponse verifyToken();
}
