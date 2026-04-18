package pharma.backend.auth.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pharma.backend.auth.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

  Optional<Role> findByName(String name);

}
