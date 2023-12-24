package com.example.userservice.validation;

import com.example.springbootmicroservicesframework.dto.AccountDto;
import com.example.springbootmicroservicesframework.exception.ValidationException;

public interface UserValidation {
    void validateAccountExisted(AccountDto account) throws ValidationException;
}
