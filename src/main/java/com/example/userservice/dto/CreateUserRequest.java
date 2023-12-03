package com.example.userservice.dto;

import com.example.springbootmicroservicesframework.utils.Const;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @NotBlank
    String username;

    @NotBlank
    @Pattern(regexp = Const.REGEX_EMAIL)
    String email;

    @NotBlank
    String password;
}
