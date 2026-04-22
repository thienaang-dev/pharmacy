package pharma.backend.auth.model;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
  private User user;

  @Override
  public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .toList();
  }

  @Override
  public @Nullable String getPassword() {
    return user.getPasswordHash();
  }

  @Override
  public @NonNull String getUsername() {
    return user.getPasswordHash();
  }
}
