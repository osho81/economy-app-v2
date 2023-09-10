package com.osho81.economyapp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService; // Class used to extract email from jwt
    private final UserDetailsService userDetailsService; // spring sec interface

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain // List of filters we need/use
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Extract token
        jwt = authHeader.substring(7); // Exclude "Bearer "
        // Extract userEmail from jwt, using special JwtService class for this:
        userEmail = jwtService.extractUsername(jwt);

        // Make sure extracted userEmail is not null,
        // AND is not already authenticated; check this by getAuth...
        if (userEmail != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Use detailService interface method to load userDetails object
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Pass in extracted jwt, check validity
            // Also pass in userDetails
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                )
            }
        }

    }
}
