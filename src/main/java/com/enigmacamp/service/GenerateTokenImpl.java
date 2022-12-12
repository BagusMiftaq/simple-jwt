package com.enigmacamp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class GenerateTokenImpl implements GenerateTokenService{
    @Value("${token.jwt-secret}")
    private String secret;

    @Value("${token.jwt-expiration}")
    private Integer jwtExpiration;

    public String generateToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .compact();
    }
}
