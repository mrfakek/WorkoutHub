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

    public WorkoutResponseDto update(Long id, WorkoutUpdateDto workoutUpdateDto, Authentication authentication) {
        Workout workoutForUpdate = workoutRepository.findById(id).orElseThrow(()->new NotFoundEntityException("Workout not found"));
        validateAccess(workoutForUpdate, authentication);
        workoutForUpdate = workoutMapper.updateWorkout(workoutUpdateDto, workoutForUpdate, exerciseRepository);
        for (WorkoutExercise exercise : workoutForUpdate.getWorkoutExercises()) {
            exercise.setWorkout(workoutForUpdate);
        }
        workoutForUpdate = workoutRepository.save(workoutForUpdate);
        return workoutMapper.toWorkoutResponseDto(workoutForUpdate);
    }

    public void delete(Long id, Authentication authentication) {
        Workout workout = workoutRepository.findById(id).orElseThrow(()->new NotFoundEntityException("Workout not found"));
        validateAccess(workout, authentication);
        workoutRepository.deleteById(id);
    }

    public List<WorkoutResponseDto> getAll(Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(()->new NotFoundEntityException("Account not found"));
        List<Workout> workouts= workoutRepository.findByOwner(account);
        if (workouts.isEmpty()) {
            throw new NotFoundEntityException("Workout not found");
        }
        return workoutMapper.toWorkoutResponseDtos(workouts);
    }

    public WorkoutResponseDto findById(Long id, Authentication authentication) {
        Workout workout = workoutRepository.findById(id).orElseThrow(()->new NotFoundEntityException("Workout not found"));
        validateAccess(workout, authentication);
        return workoutMapper.toWorkoutResponseDto(workout);
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
