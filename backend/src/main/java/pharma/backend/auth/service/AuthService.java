package pharma.backend.auth.service;

import pharma.backend.auth.dto.CreateUserRequestDto;
import pharma.backend.auth.dto.CreateUserResponseDto;
import pharma.backend.auth.dto.LoginUserRequestDto;
import pharma.backend.auth.dto.LoginUserResponseDto;

public interface AuthService {
  CreateUserResponseDto register(CreateUserRequestDto createUserRequestDto);

  LoginUserResponseDto login(LoginUserRequestDto loginUserRequestDto);
}
