package by.tms.workouthub.mappers;

import by.tms.workouthub.dto.AnthropometryCreateDto;
import by.tms.workouthub.dto.AnthropometryResponseDto;
import by.tms.workouthub.dto.AnthropometryUpdateDto;
import by.tms.workouthub.entity.Anthropometry;
import by.tms.workouthub.entity.AnthropometryHistory;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AnthropometryMapper {
    AnthropometryResponseDto toAnthropometryResponseDto(Anthropometry anthropometry);
    @Mapping(target = "id", ignore = true)
    Anthropometry toAnthropometryFromHistory(AnthropometryHistory anthropometryHistory);
    Anthropometry toAnthropometry(AnthropometryCreateDto anthropometryCreateDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Anthropometry updateAnthropometry(AnthropometryUpdateDto anthropometryUpdateDto,@MappingTarget Anthropometry anthropometry);
}
