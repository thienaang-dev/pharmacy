package pharma.backend.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class LoginUserResponseDto implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @JsonProperty("username")
  private String username;

  @JsonProperty("jwt_token")
  private String jwtToken;

  @JsonProperty("expiration")
  private LocalDateTime expiration;
}
