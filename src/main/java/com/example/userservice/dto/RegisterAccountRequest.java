package com.example.userservice.dto;

import com.example.springbootmicroservicesframework.dto.AccountDto;
import com.example.springbootmicroservicesframework.dto.PasswordDto;
import com.example.springbootmicroservicesframework.validation.PasswordMatches;
import com.example.springbootmicroservicesframework.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@PasswordMatches
public class RegisterAccountRequest implements AccountDto, PasswordDto {
    @NotBlank
    String username;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @ValidPassword
    String password;

    @NotBlank
    String confirmPassword;
}
