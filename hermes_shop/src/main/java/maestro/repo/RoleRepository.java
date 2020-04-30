package maestro.repo;

import maestro.model.Role;
import maestro.util.Constants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
