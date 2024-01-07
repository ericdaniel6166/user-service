package com.example.userservice.api;

import com.example.springbootmicroservicesframework.exception.AppNotFoundException;
import com.example.userservice.dto.ProfileDto;
import com.example.userservice.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Validated
@RequestMapping("/profile")
public class ProfileApi {

    ProfileService profileService;

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/view/{username}")
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.name")
    public ResponseEntity<ProfileDto> view(@PathVariable @NotBlank String username) throws AppNotFoundException {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return ResponseEntity.ok(profileService.view(username));
    }
}
