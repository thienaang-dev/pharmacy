package pharma.backend.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pharma.backend.auth.exception.RoleNotFoundException;
import pharma.backend.auth.model.Role;
import pharma.backend.auth.repository.RoleRepository;
import pharma.backend.auth.service.crud.impl.RoleCrudServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RoleCrudServiceImplTests {

  @Mock
  RoleRepository roleRepository;

  @InjectMocks
  RoleCrudServiceImpl roleCrudService;

  @Test
  void givenRoleIds_whenFindAllInIds_shouldReturnRoles() {
    UUID userRoleId = UUID.randomUUID();
    Role userRole = new Role(userRoleId, "USER");

    when(roleRepository.findAllById(List.of(userRoleId))).thenReturn(List.of(userRole));

    Set<Role> roles = roleCrudService.findAllInIds(List.of(userRoleId));

    assertEquals(1, roles.size());
    assertTrue(roles.contains(userRole));
  }

  @Test
  void givenInvalidRoleIds_whenFindAllInIds_shouldNotThrowException() {
    UUID randomUUID = UUID.randomUUID();

    when(roleRepository.findAllById(List.of(randomUUID))).thenReturn(List.of());

    Set<Role> roles = roleCrudService.findAllInIds(List.of(randomUUID));

    assertEquals(0, roles.size());
  }

  @Test
  void givenRoleName_whenFindByName_shouldReturnRole() {
    UUID userRoleId = UUID.randomUUID();
    Role userRole = new Role(userRoleId, "USER");

    when(roleRepository.findByName("USER")).thenReturn(Optional.of(userRole));

    Role role = roleCrudService.findByName("USER");

    assertEquals(userRole, role);
  }


  @Test
  void givenInvalidRoleName_whenFindByName_shouldThrowException() {
    when(roleRepository.findByName("WIZARD")).thenReturn(Optional.empty());

    assertThrows(RoleNotFoundException.class, () -> roleCrudService.findByName("WIZARD"));
  }
}