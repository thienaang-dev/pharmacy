package pharma.backend.auth.mapper;

import java.time.LocalDateTime;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import pharma.backend.auth.dto.CreateUserResponseDto;
import pharma.backend.auth.dto.LoginUserResponseDto;
import pharma.backend.auth.model.Role;
import pharma.backend.auth.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
  @Mappings({
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "active", ignore = true),
    @Mapping(target = "createdAt", ignore = true),
    @Mapping(target = "createdBy", ignore = true),
    @Mapping(target = "modifiedAt", ignore = true),
    @Mapping(target = "modifiedBy", ignore = true),
    @Mapping(target = "deletedAt", ignore = true),
  })
  User toEntity(String username, String passwordHash, Set<Role> roles);

  CreateUserResponseDto toCreateUserResponseDto(User user);

  LoginUserResponseDto toLoginUserResponseDto(
      String username, String jwtToken, LocalDateTime expiration);
}
