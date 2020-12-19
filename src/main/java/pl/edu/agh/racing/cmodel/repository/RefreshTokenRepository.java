package pl.edu.agh.racing.cmodel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenRepository, Long> {
}
