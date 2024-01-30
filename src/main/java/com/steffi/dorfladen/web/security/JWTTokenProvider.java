package com.steffi.dorfladen.web.security;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenProvider {

    private final Key jwtSchluessel;

    public JWTTokenProvider(PasswortHashing passwortHashing) {
        jwtSchluessel = passwortHashing.gSecretKey();
    }

    public String generateToken(String benutzername) {

        var jetzt = Instant.now();
        var ablaufzeit = jetzt.plusSeconds(900);

        return Jwts.builder()
                .setSubject(benutzername)
                .setIssuedAt(Date.from(jetzt))
                .setExpiration(Date.from(ablaufzeit))
                .signWith(jwtSchluessel, SignatureAlgorithm.HS512)
                .compact();

    }

    public String generateToken(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return generateToken(user.getUsername());
    }

    public String getBenutzername(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(jwtSchluessel)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSchluessel)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            // TODO: Hier die richtigen Exceptions herausfinden und werfen
        }
        return false;
    }

}