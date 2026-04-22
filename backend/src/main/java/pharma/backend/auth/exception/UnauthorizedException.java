package pharma.backend.auth.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException() {
    super("Invalid username or password");
  }
}
