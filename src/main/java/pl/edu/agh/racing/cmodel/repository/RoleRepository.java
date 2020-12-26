package pl.edu.agh.racing.cmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.racing.cmodel.model.ERole;
import pl.edu.agh.racing.cmodel.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRole(ERole role);
}
