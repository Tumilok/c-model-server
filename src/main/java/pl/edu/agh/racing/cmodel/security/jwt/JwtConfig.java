package pl.edu.agh.racing.cmodel.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfig {
    private String secretKey;
    private int expirationTimeInDays;
}
