package pl.edu.agh.racing.cmodel.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.edu.agh.racing.cmodel.exception.CModelException;
import pl.edu.agh.racing.cmodel.security.service.UserDetailsImpl;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {

    // TODO: Move jwtSecret to the application.properties file

    private final String jwtSecret = "someSecretKeyVerySecretsomeSecretKeyVerySecretsomeSecretKeyVerySecretsomeSecretKeyVerySecretsomeSecretKeyVerySecret";

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
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
