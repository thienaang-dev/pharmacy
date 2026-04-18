package pharma.backend.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import pharma.backend.common.dto.ErrorDto;

@Tag(name = "Authentication Controller", description = "Controller for authentication")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "Register a new user")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Successfully registered a new user",
            content =
                @Content(
                    schema = @Schema(implementation = CreateUserResponseDto.class),
                    mediaType = "application/json")),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content =
                @Content(
                    schema = @Schema(implementation = ErrorDto.class),
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            value = "{\"code\": \"400\", \"message\": \"Invalid input data\"}"))),
        @ApiResponse(
            responseCode = "409",
            description = "User already exists",
            content =
                @Content(
                    schema = @Schema(implementation = ErrorDto.class),
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            value = "{\"code\": \"409\", \"message\": \"User already exists\"}")))
      })
  @PostMapping("/register")
  private ResponseEntity<CreateUserResponseDto> register(
      @Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
    log.info("REST request to register a new user");
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authService.register(createUserRequestDto));
  }
}
