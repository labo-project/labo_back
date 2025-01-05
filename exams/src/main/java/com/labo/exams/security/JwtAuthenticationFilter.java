package com.labo.exams.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractTokenFromRequest(request);
            if (token != null && validateToken(token)) {
                Claims claims = extractClaims(token);
                setSecurityContext(claims, token); // Modified to include token
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Authentication error: ", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // Log the exception for debugging
            System.err.printf("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    private Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder() // New method to avoid deprecation
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8))) // Use a properly
                                                                                                   // formatted key
                    .build()
                    .parseClaimsJws(token) // Parse the token
                    .getBody();
        } catch (JwtException e) {
            // Log the exception for easier debugging
            System.err.printf("Failed to parse JWT claims: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JWT token", e); // Or handle appropriately
        }
    }

    private void setSecurityContext(Claims claims, String token) {
        String username = claims.getSubject();
        List<?> rolesRaw = claims.get("roles", List.class);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (rolesRaw != null) {
            authorities = rolesRaw.stream()
                    .filter(role -> role instanceof String)
                    .map(role -> new SimpleGrantedAuthority((String) role))
                    .collect(Collectors.toList());
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, token,
                authorities);
        log.debug("Setting authentication: {}", authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
