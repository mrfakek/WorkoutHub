package by.tms.workouthub.mappers;

import by.tms.workouthub.dto.ExerciseCreateDto;
import by.tms.workouthub.dto.ExerciseResponseDto;
import by.tms.workouthub.dto.ExerciseUpdateDto;
import by.tms.workouthub.entity.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseResponseDto toExerciseResponseDto(Exercise exercise);
    @Mapping(target = "id", ignore = true)
    Exercise updateExercise(ExerciseUpdateDto exerciseUpdateDto, @MappingTarget Exercise exercise);
    Exercise toExercise(ExerciseCreateDto exerciseCreateDto);
}
