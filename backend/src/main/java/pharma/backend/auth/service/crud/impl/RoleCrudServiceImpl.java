package pharma.backend.auth.service.crud.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pharma.backend.auth.exception.RoleNotFoundException;
import pharma.backend.auth.model.Role;
import pharma.backend.auth.repository.RoleRepository;
import pharma.backend.auth.service.crud.RoleCrudService;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleCrudServiceImpl implements RoleCrudService {
  private final RoleRepository roleRepository;

  @Override
  public Set<Role> findAllInIds(List<UUID> uuids) {
    return new HashSet<>(roleRepository.findAllById(uuids));
  }

  @Override
  public Role findByName(String name) {
    return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
  }
}
