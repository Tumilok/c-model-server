package pl.edu.agh.racing.cmodel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.racing.cmodel.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
