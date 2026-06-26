package com.niladri.userservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class JWTService {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            JWTConstants.SECRET_KEY.getBytes(StandardCharsets.UTF_8)
    );

    public String generateAccessToken(Authentication authentication, Long userId) {
        return generateToken(authentication, userId);
    }

    private String generateToken(Authentication authentication, Long userId) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roles = populateAuthorities(authorities);

        return Jwts.builder()
                .issuedAt(new Date())
                .subject(userId.toString())
                .claim("email", authentication.getName())
                .claim("authorities", roles)
                .claim("userId", userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60))
                .signWith(secretKey)
                .compact();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            authoritiesSet.add(grantedAuthority.getAuthority());
        }

        return String.join(",", authoritiesSet);
    }

    public String getUserIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        String userId = getUserIdFromToken(token);
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}

