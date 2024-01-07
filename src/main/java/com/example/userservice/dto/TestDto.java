package com.example.userservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDto {
    String username;
    String preferredUsername;
    String email;
    Boolean emailVerified;
    String scope;
//    String sessionId; //improvement later
    Collection<String> authorities;
    Map<String, Object> claims;
}
