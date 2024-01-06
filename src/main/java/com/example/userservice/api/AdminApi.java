package com.example.userservice.api;

import com.example.springbootmicroservicesframework.utils.UriConst;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Validated
@RequestMapping(UriConst.ADMIN)
public class AdminApi {

    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok("test");
    }




}
