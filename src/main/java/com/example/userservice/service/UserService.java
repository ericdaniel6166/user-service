package com.example.userservice.service;

import com.example.userservice.dto.CreateUserRequest;

public interface UserService {

    String register(CreateUserRequest request);
}
