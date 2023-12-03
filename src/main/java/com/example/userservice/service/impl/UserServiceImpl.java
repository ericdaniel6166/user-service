package com.example.userservice.service.impl;

import com.example.springbootmicroservicesframework.utils.MessageConstant;
import com.example.userservice.dto.CreateUserRequest;
import com.example.userservice.model.UserInfo;
import com.example.userservice.repository.UserInfoRepository;
import com.example.userservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserInfoRepository userInfoRepository;
    final PasswordEncoder passwordEncoder;
    final ModelMapper modelMapper;
    final MessageSource messageSource;

//    final JwtService jwtService;
//
//    public String generateToken(String username) {
//        return jwtService.generateToken(username);
//    }
//
//    public void validateToken(String token) {
//        jwtService.validateToken(token);
//    }


    @Transactional
    @Override
    public String register(CreateUserRequest request) {
        UserInfo userInfo = modelMapper.map(request, UserInfo.class);
        userInfo.setPassword(passwordEncoder.encode(request.getPassword()));
        userInfoRepository.saveAndFlush(userInfo);
        String obj = messageSource.getMessage(MessageConstant.MGS_OBJ_ACCOUNT, null, LocaleContextHolder.getLocale());
        String msg = messageSource.getMessage(MessageConstant.MSG_INF_CREATED, new String[]{obj}, LocaleContextHolder.getLocale());
        return StringUtils.capitalize(msg);
    }
}
