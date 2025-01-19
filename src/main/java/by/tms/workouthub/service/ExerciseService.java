package by.tms.workouthub.service;

import by.tms.workouthub.dto.ExerciseCreateDto;
import by.tms.workouthub.dto.ExerciseDeleteDto;
import by.tms.workouthub.dto.ExerciseResponseDto;
import by.tms.workouthub.entity.Exercise;
import by.tms.workouthub.exceptions.NotFoundEntityException;
import by.tms.workouthub.mappers.ExerciseMapper;
import by.tms.workouthub.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private final ExerciseMapper exerciseMapper;
    private final ExerciseRepository exerciseRepository;
    public ExerciseService(ExerciseMapper exerciseMapper, ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    public ExerciseResponseDto create(ExerciseCreateDto exerciseCreateDto) {
        Exercise exercise = exerciseMapper.toExercise(exerciseCreateDto);
        exercise = exerciseRepository.save(exercise);
        return exerciseMapper.toExerciseResponseDto(exercise);
    }

    public List<ExerciseResponseDto> getAll() {
        List<Exercise> exercises = exerciseRepository.findAll();
        if (exercises.isEmpty()) {
            throw new NotFoundEntityException("Exercises not found");
        }
        return  exercises.stream()
                .map(exerciseMapper ::toExerciseResponseDto)
                .collect(Collectors.toList());
    }

    public ExerciseResponseDto update(ExerciseCreateDto exerciseCreateDto) {
        Exercise exerciseForUpdate = exerciseRepository.findExerciseByName(exerciseCreateDto.getName())
                .orElseThrow(() -> new NotFoundEntityException("Exercise with name: "+exerciseCreateDto.getName()+ " not found"));
       exerciseForUpdate = exerciseMapper.updateExerciseFromDto(exerciseCreateDto, exerciseForUpdate);
        return exerciseMapper.toExerciseResponseDto(exerciseRepository.save(exerciseForUpdate));
    }

    public ExerciseResponseDto delete(ExerciseDeleteDto exerciseDeleteDto) {
        exerciseRepository.delete(exerciseDeleteDto.getName());
    }
}
