package pl.edu.agh.racing.cmodel.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.agh.racing.cmodel.model.Role;
import pl.edu.agh.racing.cmodel.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveUser() {
        // given
        User user1 = new User(null, "John", "Peterson",
                "john.peterson@gmail.com", "12345678", false, Role.NEWBIE);
        User user2 = new User(null, "Garik", "Jackson",
                "garik.jackson@gmail.com", "qwerty", true, Role.MODERATOR);
        User redundantUser = new User(null, "Garik", "Jackson",
                "garik.jackson@gmail.com", "qwerty", true, Role.MODERATOR);

        // when
        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);

        // then
        assertThat(savedUser1).usingRecursiveComparison().isEqualTo(user1);
        assertThat(savedUser2).usingRecursiveComparison().isEqualTo(user2);
        assertThrows(DataIntegrityViolationException.class,
                () -> userRepository.saveAndFlush(redundantUser));
    }
}
