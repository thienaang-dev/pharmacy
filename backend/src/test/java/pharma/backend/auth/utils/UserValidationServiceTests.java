package pharma.backend.auth.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pharma.backend.auth.exception.UserAlreadyExistsException;
import pharma.backend.auth.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserValidationServiceTests {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserValidationService userValidationService;

  @Test
  void givenUniqueUsername_whenValidateUsernameIsUnique_thenNoExceptionThrown() {
    when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
    assertDoesNotThrow(() -> userValidationService.validateUsernameIsUnique("testuser"));
  }

  @Test
  void givenExitingUsername_whenValidateUsernameIsUnique_thenExceptionThrown() {
    when(userRepository.findByUsername("testuser")).thenThrow(UserAlreadyExistsException.class);
    assertThrows(UserAlreadyExistsException.class,
        () -> userValidationService.validateUsernameIsUnique("testuser"));
  }
}