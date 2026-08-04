package com.example.FirstAPI.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final SecretKey key;
    public JwtService(@Value("${jwt.secret}") String secret){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UserDetails user){
        LocalDate data = LocalDate.now().plusDays(1);
        Date date = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder().subject(user.getUsername()).expiration(date).signWith(key).compact();
    }
    public String extractUsername(String token){
        return extractClaims(token, claims -> claims.getSubject());

    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }


    public Boolean isTokenValid(String token, UserDetails user){

        String username = extractUsername(token);
        Date expiration = extractExpiration(token);
        if (expiration.before(new Date()) || !(username.equals(user.getUsername()))){
            System.out.println("TOKEN IIIIINNNNNNVALIDADO");
            return false;
        }
        else {
            System.out.println("TOKEN VALIDADO");
            return true;
        }
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolver){
        Claims claim =  Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return resolver.apply(claim);

    }
}
