package com.makarenko.tasktool.security;

import static com.makarenko.tasktool.security.SecurityConstants.EXPIRATION_TIME;
import static com.makarenko.tasktool.security.SecurityConstants.SECRET;

import com.makarenko.tasktool.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  public String generateToken(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    Date now = new Date(System.currentTimeMillis());
    Date expireDate = new Date(now.getTime() + EXPIRATION_TIME);
    String userId = Long.toString(user.getId());

    Map<String, Object> claims = new HashMap<>();
    claims.put("id", (Long.toString(user.getId())));
    claims.put("username", user.getUsername());
    claims.put("fullName", user.getFullName());

    return Jwts.builder()
        .setSubject(userId)
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
      return true;
    } catch (SignatureException e) {
      System.out.println("Invalid JWT Signature");
    } catch (MalformedJwtException e) {
      System.out.println("Invalid JWT token");
    } catch (ExpiredJwtException e) {
      System.out.println("Expired JWT token");
    } catch (UnsupportedJwtException e) {
      System.out.println("Unsupported JWT token");
    } catch (IllegalArgumentException e) {
      System.out.println("JWT claims strong is empty");
    }
    return false;
  }

  public Long getUserIdFromJwt(String token) {
    Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    String id = (String) claims.get("id");
    return Long.parseLong(id);
  }
}
