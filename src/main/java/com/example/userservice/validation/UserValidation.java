package com.example.userservice.validation;

import com.example.springbootmicroservicesframework.exception.ValidationException;
import com.example.userservice.dto.AccountDto;

public interface UserValidation {
    void validateAccountExisted(AccountDto account) throws ValidationException;
}
