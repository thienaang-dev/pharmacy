package pharma.backend.auth.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pharma.backend.auth.exception.UserAlreadyExistsException;
import pharma.backend.auth.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserValidationService {

  private final UserRepository userRepository;

  public void validateUsernameIsUnique(String username) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new UserAlreadyExistsException(username);
    }
  }
}