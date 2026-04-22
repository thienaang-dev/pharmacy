package pharma.backend.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
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
public class LoginUserRequestDto implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @NotBlank(message = "Username cannot be blank")
  @JsonProperty("username")
  private String username;

  @NotBlank(message = "Password cannot be blank")
  @JsonProperty("password")
  private String password;
}
