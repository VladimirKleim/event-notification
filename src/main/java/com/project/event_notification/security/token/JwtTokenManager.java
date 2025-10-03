package com.project.event_notification.security.token;

import com.project.event_notification.security.auth.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenManager {


   private final Long tokenLifeTime;
   private final SecretKey key;


    public JwtTokenManager(
          @Value("${jwt.token.lifetime}")  Long tokenLifeTime,
          @Value("${jwt.token.signature}")  String key
    ) {
        this.tokenLifeTime = tokenLifeTime;
        this.key = Keys.hmacShaKeyFor(key.getBytes());
    }

    public String generateToken(User user) {
        Map<String, Object> props = new HashMap<>();
        props.put("role", user.role());
        Date issuedTime = new Date();
        Date expiredTime = new Date(issuedTime.getTime() + tokenLifeTime);
        return Jwts.builder()
                .signWith(key)
                .expiration(expiredTime)
                .claims(props)
                .issuedAt(issuedTime)
                .subject(user.login())
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException e) {
            return false;
        }
        return true;
    }

    public String getLoginFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    public String getIdFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload().getId();
    }
}
