package pl.edu.agh.racing.cmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.racing.cmodel.model.Simulation;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Long> {
}
