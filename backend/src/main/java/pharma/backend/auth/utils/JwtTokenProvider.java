package pharma.backend.auth.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pharma.backend.auth.model.CustomUserDetails;

@Component
public class JwtTokenProvider {
  @Value("${app.jwt.secret}")
  private String secret;

  @Value("${app.jwt.expiration-ms}")
  private String expirationMs;

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String createToken(CustomUserDetails customUserDetails) {
    return Jwts.builder()
        .setSubject(customUserDetails.getUsername())
        .setIssuer("PharmacyBackendService")
        .setExpiration(Date.from(Instant.now().plusMillis(Long.parseLong(expirationMs))))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String getUsernameFromToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean isTokenValid(String token, CustomUserDetails customUserDetails) {
    return !isTokenExpired(token) && customUserDetails != null;
  }

  public boolean isTokenExpired(String token) {
    Date tokenExpirationDate =
        Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
    return tokenExpirationDate.before(new Date());
  }
}
