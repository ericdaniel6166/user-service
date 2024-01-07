package com.example.userservice.api;

import com.example.springbootmicroservicesframework.utils.AppSecurityUtils;
import com.example.springbootmicroservicesframework.utils.UriConst;
import com.example.userservice.dto.TestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Validated
@RequestMapping(UriConst.CUSTOMER)
public class CustomerApi {


    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok(TestDto.builder()
                .username(AppSecurityUtils.getUsername())
                .preferredUsername(AppSecurityUtils.getPreferredUsername())
                .email(AppSecurityUtils.getEmail())
                .emailVerified(AppSecurityUtils.getEmailVerified())
                .scope(AppSecurityUtils.getScope())
                .authorities(AppSecurityUtils.getAuthorities())
                .claims(AppSecurityUtils.getClaims())
                .build());
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/test2")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> test2() {
        return ResponseEntity.ok("test2");
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/test3")
    @PreAuthorize("principal.claims['email_verified'] == true")
    public ResponseEntity<Object> test3() {
        return ResponseEntity.ok("test3");
    }









}
