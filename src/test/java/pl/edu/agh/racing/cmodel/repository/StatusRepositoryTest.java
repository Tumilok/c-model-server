package pl.edu.agh.racing.cmodel.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.agh.racing.cmodel.model.Status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
public class StatusRepositoryTest {

    @Autowired
    private StatusRepository statusRepository;

    @Test
    public void shouldSaveStatus() {
        // given
        Status status1 = new Status(null, "Some status");
        Status status2 = new Status(null, "Some other status");
        Status redundantStatus = new Status(null, "Some status");

        // when
        Status savedStatus1 = statusRepository.save(status1);
        Status savedStatus2 = statusRepository.save(status2);

        // then
        assertThat(savedStatus1).usingRecursiveComparison().isEqualTo(status1);
        assertThat(savedStatus2).usingRecursiveComparison().isEqualTo(status2);
        assertThrows(DataIntegrityViolationException.class,
                () -> statusRepository.saveAndFlush(redundantStatus));
    }
}
