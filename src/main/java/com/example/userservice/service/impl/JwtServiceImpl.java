package com.example.userservice.service.impl;

import com.example.userservice.config.JwtProperties;
import com.example.userservice.dto.TokenDto;
import com.example.userservice.service.JwtService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtServiceImpl implements JwtService {

    JwtProperties jwtProperties;

    @Override
    public void verifyToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        } catch (JwtException e) {
            throw new BadCredentialsException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
    }

    @Override
    public TokenDto generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private TokenDto createToken(Map<String, Object> claims, String username) {
        long now = System.currentTimeMillis();
        Date expiresAt = new Date(now + jwtProperties.getExpiresIn());
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(expiresAt)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        return new TokenDto(token, LocalDateTime.ofInstant(expiresAt.toInstant(), ZoneId.systemDefault()));
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
