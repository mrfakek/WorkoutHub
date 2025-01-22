package by.tms.workouthub.service;

import by.tms.workouthub.dto.WorkoutCreateDto;
import by.tms.workouthub.dto.WorkoutResponseDto;
import by.tms.workouthub.dto.WorkoutUpdateDto;
import by.tms.workouthub.entity.Account;
import by.tms.workouthub.entity.Workout;
import by.tms.workouthub.entity.WorkoutExercise;
import by.tms.workouthub.exceptions.AccessDeniedException;
import by.tms.workouthub.exceptions.NotFoundEntityException;
import by.tms.workouthub.mappers.WorkoutMapper;
import by.tms.workouthub.repository.AccountRepository;
import by.tms.workouthub.repository.ExerciseRepository;
import by.tms.workouthub.repository.WorkoutRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    public final WorkoutRepository workoutRepository;
    public final WorkoutMapper workoutMapper;
    private final AccountRepository accountRepository;
    private final ExerciseRepository exerciseRepository;


    public WorkoutService(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper, AccountRepository accountRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.accountRepository = accountRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public WorkoutResponseDto create(WorkoutCreateDto workoutCreateDto, Authentication authentication) {
        Workout workout = workoutMapper.toWorkout(workoutCreateDto, exerciseRepository);
        workout.setOwner(accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found")));
        for (WorkoutExercise workoutExercise : workout.getWorkoutExercises()) {
            workoutExercise.setWorkout(workout);
        }
        workout = workoutRepository.save(workout);
        return workoutMapper.toWorkoutResponseDto(workout);
    }

    public WorkoutResponseDto update(WorkoutUpdateDto workoutUpdateDto, Authentication authentication) {
        return null;
    }

    public void delete(Long id, Authentication authentication) {
    }

    public List<WorkoutResponseDto> getAll(Authentication authentication) {
        return null;
    }

    public WorkoutResponseDto findById(Long id, Authentication authentication) {
        return null;
    }

    public void validateAccess(Workout workout, Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication
                        .getName())
                        .orElseThrow(() -> new NotFoundEntityException("Account not found"));
        if (!account.getId().equals(workout.getOwner().getId())) {
            throw new AccessDeniedException("Account is not the owner of this workout, Access denied.");
        }
    }
}
