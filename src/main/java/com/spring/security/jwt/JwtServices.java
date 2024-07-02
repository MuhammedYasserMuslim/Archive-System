package com.spring.security.jwt;

import com.spring.security.model.entity.AppUser;
import com.spring.security.services.UserServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServices {

    private static final String SECRET_KEY = "LLMk0WHH53SWsBi2tlLXbI1UVO7UVpult765D7GFFgeBcxPLlBAdlZIeleAkJijh";
    private static final Long EXPIRATION_TIME = 36000L;

    private final UserServices userServices;

    @Lazy
    public JwtServices(UserServices userServices) {
        this.userServices = userServices;
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extractClaim, UserDetails userDetails) {
        AppUser user = userServices.findByUserName(userDetails.getUsername());
        return Jwts.builder()
                .setClaims(extractClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * EXPIRATION_TIME)))
                .setAudience(user.getImagePath())
                .setIssuer(user.getFirstName().concat(" ").concat(user.getLastName()))
                .setId(userServices.findUserByUserName(userDetails.getUsername()).getRoles())
                .signWith(getSercurtKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenValid(String token, String username) throws   JwtException {
        final String userName = extractUserName(token);
        return (username.equals(userName) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractEXPIRATION(token).before(new Date());
    }

    private Date extractEXPIRATION(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, Claims::getId);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsTResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSercurtKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSercurtKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
