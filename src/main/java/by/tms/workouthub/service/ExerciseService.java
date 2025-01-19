package by.tms.workouthub.service;

import by.tms.workouthub.dto.ExerciseCreateDto;
import by.tms.workouthub.dto.ExerciseResponseDto;
import by.tms.workouthub.dto.ExerciseUpdateDto;
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

    public ExerciseResponseDto getById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->new NotFoundEntityException("Exercise not found"));
        return exerciseMapper.toExerciseResponseDto(exercise);
    }

    public List<ExerciseResponseDto> getAll() {
        List<Exercise> exercises = exerciseRepository.findAll();
        if (exercises.isEmpty()) {
            throw new NotFoundEntityException("Exercises not found");
        }
        return exercises.stream()
                .map(exerciseMapper::toExerciseResponseDto)
                .collect(Collectors.toList());
    }

    public ExerciseResponseDto update(ExerciseUpdateDto exerciseUpdateDto) {
        Exercise exerciseForUpdate = exerciseRepository.findById(exerciseUpdateDto.getId())
                .orElseThrow(() -> new NotFoundEntityException("Exercise not found"));
        exerciseForUpdate = exerciseMapper.updateExercise(exerciseUpdateDto, exerciseForUpdate);
        return exerciseMapper.toExerciseResponseDto(exerciseRepository.save(exerciseForUpdate));
    }

    public void delete(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Exercise not found"));
        exerciseRepository.delete(exercise);
    }
}
