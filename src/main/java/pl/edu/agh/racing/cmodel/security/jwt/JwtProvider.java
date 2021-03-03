package pl.edu.agh.racing.cmodel.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.edu.agh.racing.cmodel.exception.CModelException;
import pl.edu.agh.racing.cmodel.security.service.UserDetailsImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static java.util.Date.from;

@Service
@AllArgsConstructor
public class JwtProvider {

    private final JwtConfig jwtConfig;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getExpirationTimeInDays())))
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes()))
                .compact();
    }

    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes()))
                .setExpiration(from(Instant.now().plusMillis(jwtConfig.getExpirationTimeInDays())))
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getSecretKey().getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtConfig.getSecretKey().getBytes())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            throw new CModelException("Invalid JWT token", e.getCause());
        } catch (ExpiredJwtException e) {
            throw new CModelException("JWT token is expired", e.getCause());
        } catch (UnsupportedJwtException e) {
            throw new CModelException("JWT token is unsupported", e.getCause());
        } catch (IllegalArgumentException e) {
            throw new CModelException("JWT claims string is empty", e.getCause());
        }
    }
}
