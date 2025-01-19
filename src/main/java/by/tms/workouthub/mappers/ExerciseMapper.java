package by.tms.workouthub.mappers;

import by.tms.workouthub.dto.ExerciseCreateDto;
import by.tms.workouthub.dto.ExerciseResponseDto;
import by.tms.workouthub.entity.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseResponseDto toExerciseResponseDto(Exercise exercise);
    Exercise updateExerciseFromDto(ExerciseCreateDto exerciseCreateDto, Exercise exercise);
    Exercise toExercise(ExerciseCreateDto exerciseCreateDto);
}
