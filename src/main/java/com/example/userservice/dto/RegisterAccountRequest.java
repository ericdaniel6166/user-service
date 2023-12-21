package com.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterAccountRequest implements AccountDto {
    @NotBlank
    String username;

    @NotBlank
    @Email
    String email;

    @NotBlank
    String password;
}
