package pl.edu.agh.racing.cmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.racing.cmodel.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
