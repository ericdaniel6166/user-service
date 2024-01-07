package com.example.userservice.service.impl;

import com.example.springbootmicroservicesframework.exception.AppNotFoundException;
import com.example.springbootmicroservicesframework.service.KeycloakService;
import com.example.springbootmicroservicesframework.utils.DateTimeUtils;
import com.example.userservice.dto.ProfileDto;
import com.example.userservice.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProfileServiceImpl implements ProfileService {

    KeycloakService keycloakService;
    ModelMapper modelMapper;

    @Override
    public ProfileDto view(String username) throws AppNotFoundException {
        UserRepresentation user = keycloakService.searchUserByUsername(username)
                .orElseThrow(() -> new AppNotFoundException(String.format("username %s", username)));

        ProfileDto profile = modelMapper.map(user, ProfileDto.class);
        profile.setCreatedDate(DateTimeUtils.toLocalDateTime(profile.getCreatedTimestamp()));
        return profile;

    }
}
