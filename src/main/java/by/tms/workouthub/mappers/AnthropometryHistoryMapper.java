package by.tms.workouthub.mappers;

import by.tms.workouthub.dto.AnthropometryResponseDto;
import by.tms.workouthub.dto.AnthropometryUpdateDto;
import by.tms.workouthub.entity.Anthropometry;
import by.tms.workouthub.entity.AnthropometryHistory;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnthropometryHistoryMapper {
    @Mapping(target = "id", ignore = true)
    AnthropometryHistory toAnthropometryHistory(Anthropometry anthropometry);
    AnthropometryResponseDto toAnthropometryResponseDto(AnthropometryHistory anthropometryHistory);
    List<AnthropometryResponseDto> toAnthropometryResponseDtoList(List<AnthropometryHistory> anthropometryHistoryList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AnthropometryHistory updateAnthropometryHistory(AnthropometryUpdateDto anthropometryUpdateDto, @MappingTarget AnthropometryHistory anthropometryHistory);
}
