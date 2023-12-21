package com.example.userservice.service;

import com.example.userservice.dto.TokenDto;

public interface JwtService {

    TokenDto generateToken(String username);

    void verifyToken(String token);
}
