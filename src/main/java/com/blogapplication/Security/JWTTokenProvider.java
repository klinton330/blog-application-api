package com.blogapplication.Security;

import com.blogapplication.exception.BlogAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenProvider {

    //Fetching values from application.properties
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationDate;

    //Generate JWT Token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(System.currentTimeMillis() + Long.parseLong(jwtExpirationDate));
        System.out.println("Before Token:");
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate).signWith(key()).compact();
        System.out.println("Token:"+token);
        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //Get user name from JWT Token
    public String getUsername(String token) {
        //Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJwt(token).getBody();
        Claims claims=Jwts.parser()
                .setSigningKey(key())
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    //Validate JWT Token
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
            return true;
        }
        catch (MalformedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid JWT token");
        }
        catch (ExpiredJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Expired JWT Exception");
        }
        catch (UnsupportedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Unsupported JWT token");
        }
        catch (IllegalArgumentException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"JWT Claims String is empty");
        }
    }
}
