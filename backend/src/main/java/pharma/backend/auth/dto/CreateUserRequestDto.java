package pharma.backend.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @NotBlank(message = "Username cannot be blank")
  @JsonProperty("username")
  private String username;

  @NotBlank(message = "Password cannot be blank")
  @JsonProperty("password")
  private String password;

  @NotNull(message = "Role IDs cannot be empty")
  @JsonProperty("role_ids")
  private List<UUID> roleIds;
}
