package com.labo.iam.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration; // in seconds

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    public String generateToken(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("roles", roles) // Add roles to claims
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .sign(Algorithm.HMAC256(secret));
    }

    public String extractUsername(String token) {
        return getDecodedJWT(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (JWTVerificationException e) {
            return false; // Invalid token
        }
    }

    private boolean isTokenExpired(String token) {
        return getDecodedJWT(token).getExpiresAt().before(new Date());
    }

    private DecodedJWT getDecodedJWT(String token) {
        JWTVerifier verifier = JWT.require(getAlgorithm()).build();
        return verifier.verify(token);
    }
}