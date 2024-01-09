package com.example.userservice.service;

import com.example.springbootmicroservicesframework.exception.AppNotFoundException;
import com.example.userservice.dto.ProfileDto;

public interface ProfileService {
    ProfileDto view(String username) throws AppNotFoundException;
}
