package pharma.backend.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pharma.backend.auth.dto.CreateUserRequestDto;
import pharma.backend.auth.dto.CreateUserResponseDto;
import pharma.backend.auth.service.AuthService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  private ResponseEntity<CreateUserResponseDto> register(
      @Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
    log.info("REST request to register a new user");
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authService.register(createUserRequestDto));
  }
}