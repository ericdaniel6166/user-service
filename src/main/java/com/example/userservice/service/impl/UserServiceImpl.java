package com.example.userservice.service.impl;

import com.example.springbootmicroservicesframework.dto.MessageResponse;
import com.example.springbootmicroservicesframework.exception.ValidationException;
import com.example.springbootmicroservicesframework.utils.Const;
import com.example.springbootmicroservicesframework.utils.MessageConstant;
import com.example.springbootmicroservicesframework.utils.AppSecurityUtils;
import com.example.userservice.dto.AuthenticationResponse;
import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.RegisterAccountRequest;
import com.example.userservice.dto.TokenDto;
import com.example.userservice.model.UserInfo;
import com.example.userservice.repository.UserInfoRepository;
import com.example.userservice.service.JwtService;
import com.example.userservice.service.UserService;
import com.example.userservice.validation.UserValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserInfoRepository userInfoRepository;
    PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;
    MessageSource messageSource;
    JwtService jwtService;
    UserValidation userValidation;
    AuthenticationManager authenticationManager;
    AppSecurityUtils appSecurityUtils;

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()));
            if (authentication.isAuthenticated()) {
                TokenDto token = jwtService.generateToken(request.getUsername());
                return new AuthenticationResponse(token.getAccessToken(),null, token.getExpiresAt());
            } else {
                throw new BadCredentialsException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
    }

    @Override
    public MessageResponse verifyToken() {
        jwtService.verifyToken(appSecurityUtils.getAccessToken());
        return Const.MESSAGE_RESPONSE_OK;
    }

    @Transactional
    @Override
    public MessageResponse register(RegisterAccountRequest request) throws ValidationException {
        userValidation.validateAccountExisted(request);
        UserInfo userInfo = modelMapper.map(request, UserInfo.class);
        userInfo.setPassword(passwordEncoder.encode(request.getPassword()));
        userInfoRepository.saveAndFlush(userInfo);
        String res = messageSource.getMessage(MessageConstant.MGS_RES_ACCOUNT, null, LocaleContextHolder.getLocale());
        String msg = messageSource.getMessage(MessageConstant.MSG_INF_RESOURCE_CREATED, new String[]{res}, LocaleContextHolder.getLocale());
        return new MessageResponse(StringUtils.capitalize(msg));
    }
}
