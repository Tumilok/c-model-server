package pl.edu.agh.racing.cmodel.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.agh.racing.cmodel.model.RefreshToken;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class RefreshTokenRepositoryTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    // TODO: Fix RefreshTokenRepositoryTest

    @Test
    public void shouldSaveRefreshToken() {
        // given
        RefreshToken refreshToken1 = new RefreshToken(null, "some token", Instant.now());
        RefreshToken refreshToken2 = new RefreshToken(null, "some other token", Instant.now());

        // when
        RefreshToken savedToken1 = refreshTokenRepository.save(refreshToken1);
        RefreshToken savedToken2 = refreshTokenRepository.save(refreshToken2);

        // then
        assertThat(savedToken1).usingRecursiveComparison().isEqualTo(refreshToken1);
        assertThat(savedToken2).usingRecursiveComparison().isEqualTo(refreshToken2);
    }
}
