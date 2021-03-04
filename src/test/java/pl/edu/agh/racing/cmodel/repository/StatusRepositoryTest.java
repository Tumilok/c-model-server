package pl.edu.agh.racing.cmodel.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class StatusRepositoryTest {

    @Autowired
    private StatusRepository statusRepository;

    // TODO: Fix StatusRepositoryTest

//    @Test
//    public void shouldSaveStatus() {
//        // given
//        Status status1 = new Status(null, "Some status");
//        Status status2 = new Status(null, "Some other status");
//        Status redundantStatus = new Status(null, "Some status");
//
//        // when
//        Status savedStatus1 = statusRepository.save(status1);
//        Status savedStatus2 = statusRepository.save(status2);
//
//        // then
//        assertThat(savedStatus1).usingRecursiveComparison().isEqualTo(status1);
//        assertThat(savedStatus2).usingRecursiveComparison().isEqualTo(status2);
//        assertThrows(DataIntegrityViolationException.class,
//                () -> statusRepository.saveAndFlush(redundantStatus));
//    }
}
