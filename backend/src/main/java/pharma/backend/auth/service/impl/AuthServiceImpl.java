package pharma.backend.auth.service.impl;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pharma.backend.auth.dto.CreateUserRequestDto;
import pharma.backend.auth.dto.CreateUserResponseDto;
import pharma.backend.auth.dto.LoginUserRequestDto;
import pharma.backend.auth.dto.LoginUserResponseDto;
import pharma.backend.auth.mapper.UserMapper;
import pharma.backend.auth.model.CustomUserDetails;
import pharma.backend.auth.model.Role;
import pharma.backend.auth.model.User;
import pharma.backend.auth.service.AuthService;
import pharma.backend.auth.service.crud.RoleCrudService;
import pharma.backend.auth.service.crud.UserCrudService;
import pharma.backend.auth.utils.CustomUserDetailsService;
import pharma.backend.auth.utils.JwtTokenProvider;
import pharma.backend.auth.utils.UserValidationService;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
  private final PasswordEncoder passwordEncoder;
  private final RoleCrudService roleCrudService;
  private final UserCrudService userCrudService;
  private final UserMapper userMapper;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserValidationService userValidationService;
  private final CustomUserDetailsService customUserDetailsService;

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

  @Override
  public LoginUserResponseDto login(LoginUserRequestDto loginUserRequestDto) {
    // Validate username and password in request
    userValidationService.validateUsernameAndPassword(
        loginUserRequestDto.getUsername(), loginUserRequestDto.getPassword());

    CustomUserDetails userDetails =
        customUserDetailsService.loadUserByUsername(loginUserRequestDto.getUsername());
    String token = jwtTokenProvider.createToken(userDetails);

    return userMapper.toLoginUserResponseDto(
        userDetails.getUsername(), token, LocalDateTime.now().plusDays(1));
  }
}
