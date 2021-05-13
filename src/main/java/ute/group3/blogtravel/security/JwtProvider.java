package ute.group3.blogtravel.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import static io.jsonwebtoken.Jwts.parser;


import java.sql.Date;
import java.time.Instant;

import static java.util.Date.from;

@Service
public class JwtProvider {
    private final String JWT_SECRET = "jsontoken"; // đoạn bí mật, chỉ server biết

    private final long JWT_Expiration= 604800000L; // thời gian hiệu lực

    public String generateToken(Authentication authentication){
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder().setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .setExpiration(Date.from(Instant.now().plusMillis(JWT_Expiration)))
                .compact();
    }
    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .setExpiration(Date.from(Instant.now().plusMillis(JWT_Expiration)))
                .compact();
    }
    public boolean validateToken(String jwt){ // Xác thực token
        Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt);
        return true;
    }
    public String getUsernameFromJwt(String token){ // lấy thông tin từ token
        Claims claims= parser().setSigningKey(JWT_SECRET).parseClaimsJwt(token).getBody();
        return  claims.getSubject();
    }

    public long getJwtExpirationInMillis() {
        return JWT_Expiration;
    }
}
