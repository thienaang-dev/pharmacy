package pharma.backend.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pharma.backend.auth.model.User;
import pharma.backend.auth.repository.UserRepository;
import pharma.backend.auth.service.crud.impl.UserCrudServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserCrudServiceImplTests {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserCrudServiceImpl userCrudService;

  @Test
  void givenUser_whenSave_shouldReturnUser() {
    UUID id = UUID.randomUUID();
    User user = new User("testexample", "password", null);
    User savedUser = user;
    savedUser.setId(id);

    when(userRepository.save(user)).thenReturn(savedUser);
    User resultUser = userCrudService.save(user);

    assertEquals(id, resultUser.getId());
    assertEquals("testexample", resultUser.getUsername());
    assertEquals("password", resultUser.getPasswordHash());
  }
}