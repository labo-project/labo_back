package com.labo.iam.security;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    
    @Override
    public void commence(HttpServletRequest request, 
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // This method is called whenever an exception is thrown due to an unauthenticated user trying to access a resource that requires authentication
        
        // Send an unauthorized error (401)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
