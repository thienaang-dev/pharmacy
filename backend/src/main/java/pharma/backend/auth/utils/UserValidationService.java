package pharma.backend.auth.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pharma.backend.auth.exception.UnauthorizedException;
import pharma.backend.auth.exception.UserAlreadyExistsException;
import pharma.backend.auth.model.User;
import pharma.backend.auth.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserValidationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void validateUsernameIsUnique(String username) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new UserAlreadyExistsException(username);
    }
  }

  public void validateUsernameAndPassword(String username, String password) {
    User user = userRepository.findByUsername(username).orElseThrow(UnauthorizedException::new);

    if (!passwordEncoder.matches(password, user.getPasswordHash())) {
      throw new UnauthorizedException();
    }
  }
}
