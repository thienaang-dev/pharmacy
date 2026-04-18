package pharma.backend.auth.service.crud;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import pharma.backend.auth.model.Role;

public interface RoleCrudService {

  Set<Role> findAllInIds(List<UUID> uuids);

  Role findByName(String name);
}