package com.IshuVastralay.EcommerceShop.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {
    public JwtProvider(){}
    SecretKey secretKey= Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());
    public String generateToken(Authentication authentication){
        String jwt= Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+846000000))
                .claim("email",authentication.getName())
                .signWith(secretKey).compact();
        return jwt;
    }
    public String getEmailFromToken(String jwt){
        jwt=jwt.substring(7);
        Claims claims=Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(jwt).getBody();
        String email=String.valueOf(claims.get("email"));
        return email;
    }

}
