package com.example.userservice.config;

import com.example.springbootmicroservicesframework.config.security.SecurityProps;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@ConditionalOnProperty(name = "security.enabled", havingValue = "true")
public class SecurityConfig {

    SecurityProps securityProps;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(securityProps.getSkipUrls()).permitAll()
                        .requestMatchers("/customer/**").hasRole("CUSTOMER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .formLogin(AbstractHttpConfigurer::disable)
                .build();
    }


}
