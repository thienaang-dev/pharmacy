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
public class RoleExceptionHandler {

  private final ErrorMapper errorMapper;

  @ExceptionHandler(RoleNotFoundException.class)
  public ResponseEntity<ErrorDto> handleRoleNotFoundException(RoleNotFoundException ex) {
    ErrorDto errorDto = errorMapper.toDto(HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
  }
}