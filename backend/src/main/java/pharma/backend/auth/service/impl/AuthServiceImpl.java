package pharma.backend.auth.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pharma.backend.auth.dto.CreateUserRequestDto;
import pharma.backend.auth.dto.CreateUserResponseDto;
import pharma.backend.auth.mapper.UserMapper;
import pharma.backend.auth.model.Role;
import pharma.backend.auth.model.User;
import pharma.backend.auth.service.AuthService;
import pharma.backend.auth.service.crud.RoleCrudService;
import pharma.backend.auth.service.crud.UserCrudService;
import pharma.backend.auth.utils.UserValidationService;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final PasswordEncoder passwordEncoder;
  private final UserValidationService userValidationService;
  private final RoleCrudService roleCrudService;
  private final UserCrudService userCrudService;
  private final UserMapper userMapper;

  @Override
  public CreateUserResponseDto register(CreateUserRequestDto createUserRequestDto) {
    // Validate username in request is unique
    userValidationService.validateUsernameIsUnique(createUserRequestDto.getUsername());

    // Retrieve roles from ids in request. If empty, default to USER
    Set<Role> roles = roleCrudService.findAllInIds(createUserRequestDto.getRoleIds());
    if (roles.isEmpty()) {
      roles.add(roleCrudService.findByName("USER"));
    }

    // Hash password
    String passwordHash = passwordEncoder.encode(createUserRequestDto.getPassword());
    User user = userMapper.toEntity(createUserRequestDto.getUsername(), passwordHash, roles);
    return userMapper.toCreateUserResponseDto(userCrudService.save(user));
  }
}