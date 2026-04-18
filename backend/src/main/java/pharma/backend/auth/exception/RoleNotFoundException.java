package pharma.backend.auth.exception;

public class RoleNotFoundException extends RuntimeException {

  public RoleNotFoundException(String name) {
    super("Role " + name + " not found");
  }
}