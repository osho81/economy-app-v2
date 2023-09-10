package com.osho81.economyapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

// Service class for extracting userName/email from token

@Service
public class JwtService {

    // Temporary secret key, generated online (256-bit, with hex);
    // https://seanwasere.com/generate-random-hex/
    private static final String SECRET_KEY =
            "669d677d375245f06cbbb53054c0094661c78d8b418d714876d774b2b505aa63";

    // From a
    public String extractUsername(String token) {
        // Extract userName/email (subject):
        return extractClaim(token, Claims::getSubject);
    }

    // Generic method
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Set expiration in 24 hours or any other desired duration
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
    }

    // Extract jwt claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) // see setSignInKey()
                .build()
                // parse the token and get it; incl. the claim of who it claims to be
                .parseClaimsJws(token) // jws = json web signature
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    ////---- Additional methods to check different issues with the token ----////



}







