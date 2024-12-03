package com.example.pratham.Secure.User.Management.System.Project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secretKey="hqfs0#2133786nfaryasvedbvsdqpedvvzffe";
    private final long expirationMs = 3600000; // 1 hour

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationMs))
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
    }
    public String extraUsername(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean isValidToken(String token){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
          return false;
        }
    }

}
