package by.tms.workouthub.mappers;

import by.tms.workouthub.dto.AnthropometryCreateDto;
import by.tms.workouthub.dto.AnthropometryResponseDto;
import by.tms.workouthub.dto.AnthropometryUpdateDto;
import by.tms.workouthub.entity.Anthropometry;
import by.tms.workouthub.entity.AnthropometryHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnthropometryMapper {
    AnthropometryResponseDto toAnthropometryResponseDto(Anthropometry anthropometry);
    Anthropometry toAnthropometry(AnthropometryCreateDto anthropometryCreateDto);
    @Mapping(target = "id", ignore = true)
    AnthropometryHistory toAnthropometryHistory(Anthropometry anthropometry);
    List<AnthropometryResponseDto> toAnthropometryResponseDtoList(List<AnthropometryHistory> anthropometryHistoryList);
    Anthropometry updateAnthropometry(AnthropometryUpdateDto anthropometryUpdateDto,@MappingTarget Anthropometry anthropometry);
}
