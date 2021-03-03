package pl.edu.agh.racing.cmodel.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.racing.cmodel.model.Simulation;

import java.util.List;
import java.util.Optional;

@Repository
public interface SimulationRepository extends PagingAndSortingRepository<Simulation, Long> {

    Optional<Simulation> findById(Long id);

    List<Simulation> findAll();
}
