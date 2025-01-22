package by.tms.workouthub.controller;

import by.tms.workouthub.dto.WorkoutCreateDto;
import by.tms.workouthub.dto.WorkoutResponseDto;
import by.tms.workouthub.dto.WorkoutUpdateDto;
import by.tms.workouthub.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {
    public final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDto> createWorkout(@RequestBody WorkoutCreateDto workoutCreateDto, Authentication authentication) {
        return new ResponseEntity<>(workoutService.create(workoutCreateDto, authentication), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDto>> getWorkouts(Authentication authentication) {
        return new ResponseEntity<>(workoutService.getAll(authentication), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponseDto> getWorkoutById(@PathVariable long id, Authentication authentication) {
        return new ResponseEntity<>(workoutService.findById(id, authentication), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<WorkoutResponseDto> updateWorkout(@RequestBody WorkoutUpdateDto workoutUpdateDto, Authentication authentication) {
        return new ResponseEntity<>(workoutService.update(workoutUpdateDto, authentication), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable long id, Authentication authentication) {
        workoutService.delete(id, authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
