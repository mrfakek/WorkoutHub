package by.tms.workouthub.mappers;

import by.tms.workouthub.dto.WorkoutCreateDto;
import by.tms.workouthub.dto.WorkoutExerciseDto;
import by.tms.workouthub.dto.WorkoutResponseDto;
import by.tms.workouthub.dto.WorkoutUpdateDto;
import by.tms.workouthub.entity.Exercise;
import by.tms.workouthub.entity.Workout;
import by.tms.workouthub.entity.WorkoutExercise;
import by.tms.workouthub.exceptions.NotFoundEntityException;
import by.tms.workouthub.repository.ExerciseRepository;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ExerciseRepository.class})
public interface WorkoutMapper {

    WorkoutResponseDto toWorkoutResponseDto(Workout workout);

    @Mapping(target = "workoutExercises", source = "workoutExercises")
    Workout toWorkout(WorkoutCreateDto workoutCreateDto, @Context ExerciseRepository exerciseRepository);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "owner", ignore = true)
    })
    Workout updateWorkout(WorkoutUpdateDto workoutUpdateDto, @MappingTarget Workout workout, @Context ExerciseRepository exerciseRepository);

    @Mapping(target = "exercise", expression = "java(toExercise(workoutExerciseDto, exerciseRepository))")
    WorkoutExercise toWorkoutExercise(WorkoutExerciseDto workoutExerciseDto, @Context ExerciseRepository exerciseRepository);

    @Mapping(target = "exerciseName", source = "exercise.name")
    WorkoutExerciseDto toWorkoutExerciseDto(WorkoutExercise workoutExercise);
    List<WorkoutResponseDto> toWorkoutResponseDtos(List<Workout> workouts);
    List<WorkoutExerciseDto> toWorkoutExerciseDtoList(List<WorkoutExercise> workoutExercises);
    List<WorkoutExercise> toWorkoutExercises(List<WorkoutExerciseDto> workoutExerciseDtos, @Context ExerciseRepository exerciseRepository);

    default Exercise toExercise(WorkoutExerciseDto workoutExerciseDto, @Context ExerciseRepository exerciseRepository) {
        return exerciseRepository.findExerciseByName(
                        workoutExerciseDto.getExerciseName())
                        .orElseThrow(() -> new NotFoundEntityException("Exercise not found"));
    }

}
