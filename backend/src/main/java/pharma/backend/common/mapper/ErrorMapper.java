package pharma.backend.common.mapper;

import org.mapstruct.Mapper;
import pharma.backend.common.dto.ErrorDto;

@Mapper(componentModel = "spring")
public interface ErrorMapper {

  ErrorDto toDto(String code, String message);
}