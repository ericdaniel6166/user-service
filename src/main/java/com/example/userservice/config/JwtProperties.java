package com.example.userservice.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ConditionalOnProperty(name = "spring.security.jwt.enabled", havingValue = "true")
@ConfigurationProperties(prefix = "spring.security.jwt")
public class JwtProperties {
    Long expiresIn;
    String secret;

}
