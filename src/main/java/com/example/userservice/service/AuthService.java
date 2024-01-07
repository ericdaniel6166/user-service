package com.example.userservice.service;

import com.example.springbootmicroservicesframework.dto.MessageResponse;
import com.example.springbootmicroservicesframework.exception.AppValidationException;
import com.example.userservice.dto.RegisterAccountRequest;

public interface AuthService {
    MessageResponse register(RegisterAccountRequest request) throws AppValidationException;
}
