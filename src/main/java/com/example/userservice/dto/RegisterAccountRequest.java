package com.example.userservice.dto;

import com.example.springbootmicroservicesframework.dto.AccountDto;
import com.example.springbootmicroservicesframework.dto.PasswordDto;
import com.example.springbootmicroservicesframework.utils.Const;
import com.example.springbootmicroservicesframework.validation.PasswordMatches;
import com.example.springbootmicroservicesframework.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@PasswordMatches
public class RegisterAccountRequest implements AccountDto, PasswordDto {
    @NotBlank
    @Size(max = Const.DEFAULT_SIZE_MAX_STRING)
    String username;

    @NotBlank
    @Email
    @Size(max = Const.DEFAULT_SIZE_MAX_STRING)
    String email;

    @NotBlank
    @ValidPassword
    @Size(max = Const.DEFAULT_SIZE_MAX_STRING)
    String password;

    @NotBlank
    @Size(max = Const.DEFAULT_SIZE_MAX_STRING)
    String confirmPassword;
}
