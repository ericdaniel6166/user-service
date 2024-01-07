package com.example.userservice.service;

import com.example.springbootmicroservicesframework.dto.MessageResponse;
import com.example.springbootmicroservicesframework.exception.AppValidationException;
import com.example.userservice.dto.RegisterAccountRequest;

public interface UserService {
    MessageResponse register(RegisterAccountRequest request) throws AppValidationException;
}
