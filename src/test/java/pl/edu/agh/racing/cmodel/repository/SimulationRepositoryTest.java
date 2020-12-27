package pl.edu.agh.racing.cmodel.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.agh.racing.cmodel.model.Simulation;
import pl.edu.agh.racing.cmodel.model.Status;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class SimulationRepositoryTest {

    @Autowired
    private SimulationRepository simulationRepository;

    // TODO: Fix SimulationRepositoryTest

//    @Test
//    public void shouldSaveSimulation() {
//        // given
//        Status status = new Status(null, "Some status");
//
//        Simulation simulation1 = new Simulation(null, "Some name", null,
//                null, null, null, null, null, null, null ,null, null, status);
//        Simulation simulation2 = new Simulation(null, "Some name", "fw",
//                "rw", "s", "diff", "u", "n", 234.2, 543.7 ,43.2, 443.2, status);
//
//        // when
//        Simulation savedSimulation1 = simulationRepository.save(simulation1);
//        Simulation savedSimulation2 = simulationRepository.save(simulation2);
//
//        // then
//        assertThat(savedSimulation1).usingRecursiveComparison().isEqualTo(simulation1);
//        assertThat(savedSimulation2).usingRecursiveComparison().isEqualTo(simulation2);
//    }
}
