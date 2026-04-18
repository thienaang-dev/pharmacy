package pharma.backend.auth.service;

import pharma.backend.auth.dto.CreateUserRequestDto;
import pharma.backend.auth.dto.CreateUserResponseDto;

public interface AuthService {

  CreateUserResponseDto register(CreateUserRequestDto createUserRequestDto);
}