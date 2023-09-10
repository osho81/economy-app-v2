package com.osho81.economyapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

// Service class for extracting userName/email from token

@Service
public class JwtService {

    // From a
    public String extractUsername(String token) {
        return null;
    }

    // Extract jwt claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws() // jws = json web signature
                .getBody(); // parse the token and get it
    }
}
