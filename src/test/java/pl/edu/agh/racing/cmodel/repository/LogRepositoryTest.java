package pl.edu.agh.racing.cmodel.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.agh.racing.cmodel.model.*;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class LogRepositoryTest {

    @Autowired
    private LogRepository logRepository;

    // TODO: Fix LogRepositoryTest

//    @Test
//    public void shouldSaveLog() {
//        // given
//        Status status = new Status(null, "Some status");
//        Simulation simulation = new Simulation(null, "Some name", null,
//                null, null, null, null, null, null, null ,null, null, status);
//        User user = new User(null, "John", "Peterson",
//                "john.peterson@gmail.com", "12345678", false, ERole.NEWBIE);
//
//        Log log1 = new Log(null, EOperation.ADD, Instant.now(), simulation, user);
//        Log log2 = new Log(null, EOperation.UPDATE, Instant.now(), simulation, user);
//        Log sameLog = new Log(null, EOperation.UPDATE, Instant.now(), simulation, user);
//
//        // when
//        Log savedLog1 = logRepository.save(log1);
//        Log savedLog2 = logRepository.save(log2);
//        Log savedLog3 = logRepository.save(sameLog);
//
//        // then
//        assertThat(savedLog1).usingRecursiveComparison().isEqualTo(log1);
//        assertThat(savedLog2).usingRecursiveComparison().isEqualTo(log2);
//        assertThat(savedLog3).usingRecursiveComparison().isEqualTo(sameLog);
//    }
}
