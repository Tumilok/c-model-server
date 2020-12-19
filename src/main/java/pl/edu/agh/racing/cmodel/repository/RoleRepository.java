package pl.edu.agh.racing.cmodel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.racing.cmodel.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
