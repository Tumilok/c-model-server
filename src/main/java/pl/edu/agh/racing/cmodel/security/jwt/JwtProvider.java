package pl.edu.agh.racing.cmodel.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.edu.agh.racing.cmodel.exception.CModelException;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {

    private final String jwtSecret = "someSecretKeyVerySecretsomeSecretKeyVerySecretsomeSecretKeyVerySecretsomeSecretKeyVerySecretsomeSecretKeyVerySecret";

    public String generateJwtToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((principal.getUsername()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
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
