package com.osho81.economyapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// Service class for extracting userName/email from token

@Service
public class JwtService {

    // Temporary secret key, generated online (256-bit, with hex);
    // https://seanwasere.com/generate-random-hex/
//    private static final String SECRET_KEY =
//            "669d677d375245f06cbbb53054c0094661c78d8b418d714876d774b2b505aa63";


    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    // Extract username from token
    public String extractUsername(String token) {
        // Extract userName/email (subject):
        return extractClaim(token, Claims::getSubject);
    }

    // Use this if only generate userDetails (overloaded)
    public String generateToken(UserDetails userDetails) {
        // Use the other generateToken method; pass in an empty map
        return generateToken(new HashMap<>(), userDetails);
    }

    // use this if have additional extra claims (overloaded)
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Set expiration duration
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                // Set which keys to sign this procedure step with:
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();  // Generates and returns the token
    }

    // Validation of token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        // Call the main method above; extract token username
        final String username = extractUsername(token);
        // Compare extracted token username with userDetails username;
        // If userName is same as userName in userDetails, and not expired -> return true
        return (username.equals((userDetails.getUsername())) && !isTokenExpired(token));
    }

    // Check if token has expired
    private boolean isTokenExpired(String token) {
        // Gets extracted token expiration date/time for the token
        // Checks if that date/time is before current date/time
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        // Returns a date/time to isTokenExpired, using extractClaim()
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic method, for extracting claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Use extractAllClaims()
        return claimsResolver.apply(claims);
    }

    // Extract ALL jwt claims
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
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}







