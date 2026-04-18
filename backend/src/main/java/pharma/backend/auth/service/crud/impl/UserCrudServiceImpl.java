package pharma.backend.auth.service.crud.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pharma.backend.auth.model.User;
import pharma.backend.auth.repository.UserRepository;
import pharma.backend.auth.service.crud.UserCrudService;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserCrudServiceImpl implements UserCrudService {

  private final UserRepository userRepository;

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }
}