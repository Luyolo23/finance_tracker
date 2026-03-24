package com.finance.tracker.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "bXktc3VwZXItc2VjcmV0LWtleS1teS1zdXBlci1zZWNyZXQta2V5";

    private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }
}
