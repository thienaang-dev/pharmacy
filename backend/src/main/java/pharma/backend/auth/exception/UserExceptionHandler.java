package pharma.backend.auth.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pharma.backend.common.dto.ErrorDto;
import pharma.backend.common.mapper.ErrorMapper;

@RequiredArgsConstructor
@RestControllerAdvice
@Order(10)
public class UserExceptionHandler {
  private final ErrorMapper errorMapper;

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorDto> handeUserAlreadyExistsException(UserAlreadyExistsException ex) {
    ErrorDto errorDto = errorMapper.toDto(HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
    ErrorDto errorDto = errorMapper.toDto(HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ErrorDto> handleUnauthorizedException(UnauthorizedException ex) {
    ErrorDto errorDto =
        errorMapper.toDto(HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
  }
}
