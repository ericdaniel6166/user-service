package com.example.userservice.validation.impl;

import com.example.springbootmicroservicesframework.dto.AccountDto;
import com.example.springbootmicroservicesframework.exception.ValidationErrorDetail;
import com.example.springbootmicroservicesframework.exception.ValidationException;
import com.example.springbootmicroservicesframework.utils.Const;
import com.example.springbootmicroservicesframework.utils.MessageConstant;
import com.example.userservice.repository.AppUserRepository;
import com.example.userservice.validation.UserValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserValidationImpl implements UserValidation {

    AppUserRepository appUserRepository;
    MessageSource messageSource;

    @Override
    public void validateAccountExisted(AccountDto account) throws ValidationException {
        Set<ValidationErrorDetail> errorDetails = new HashSet<>();
        if (Boolean.TRUE.equals(appUserRepository.existsByUsername(account.getUsername()))) {
            String res = messageSource.getMessage(MessageConstant.MGS_RES_USERNAME, null, LocaleContextHolder.getLocale());
            String msg = messageSource.getMessage(MessageConstant.MSG_ERR_RESOURCE_EXISTED, new String[]{res}, LocaleContextHolder.getLocale());
            errorDetails.add(new ValidationErrorDetail(Const.FIELD_USERNAME, null, StringUtils.capitalize(msg)));
        }
        if (Boolean.TRUE.equals(appUserRepository.existsByEmail(account.getEmail()))) {
            String res = messageSource.getMessage(MessageConstant.MGS_RES_EMAIL, null, LocaleContextHolder.getLocale());
            String msg = messageSource.getMessage(MessageConstant.MSG_ERR_RESOURCE_EXISTED, new String[]{res}, LocaleContextHolder.getLocale());
            errorDetails.add(new ValidationErrorDetail(Const.FIELD_EMAIL, null, StringUtils.capitalize(msg)));
        }

        if (CollectionUtils.isNotEmpty(errorDetails)) {
            throw new ValidationException(new ArrayList<>(errorDetails));
        }


    }
}
