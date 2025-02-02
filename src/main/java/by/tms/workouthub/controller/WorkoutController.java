package by.tms.workouthub.controller;

import by.tms.workouthub.dto.WorkoutCreateDto;
import by.tms.workouthub.dto.WorkoutResponseDto;
import by.tms.workouthub.dto.WorkoutUpdateDto;
import by.tms.workouthub.service.WorkoutService;
import by.tms.workouthub.validation.annotation.ValidateThis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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

    @Operation(summary = "Create a new workout", responses = {
            @ApiResponse(description = "Workout successfully created", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkoutResponseDto.class))),
            @ApiResponse(description = "Bad request", responseCode = "400"),
    })
    @PostMapping
    public ResponseEntity<WorkoutResponseDto> createWorkout(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data for updating the workout", required = true)
                                                                @RequestBody @Valid WorkoutCreateDto workoutCreateDto,
                                                                Authentication authentication) {
        return new ResponseEntity<>(workoutService.create(workoutCreateDto, authentication), HttpStatus.OK);
    }

    @Operation(summary = "Get all workouts", responses = {
            @ApiResponse(description = "List of workouts", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkoutResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<WorkoutResponseDto>> getWorkouts(Authentication authentication) {
        return new ResponseEntity<>(workoutService.getAll(authentication), HttpStatus.OK);
    }

    @Operation(summary = "Get workout by ID", responses = {
            @ApiResponse(description = "Workout found", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkoutResponseDto.class))),
            @ApiResponse(description = "Access denied", responseCode = "403"),
            @ApiResponse(description = "Workout not found", responseCode = "404")

    })
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponseDto> getWorkoutById(@PathVariable Long id, Authentication authentication) {
        return new ResponseEntity<>(workoutService.findById(id, authentication), HttpStatus.OK);
    }

    @Operation(summary = "Update workout by ID", responses = {
            @ApiResponse(description = "Workout successfully updated", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkoutResponseDto.class))),
            @ApiResponse(description = "Bad request", responseCode = "400"),
            @ApiResponse(description = "Access denied", responseCode = "403"),
            @ApiResponse(description = "Workout not found", responseCode = "404")

    })
    @ValidateThis
    @PatchMapping("/{id}")
    public ResponseEntity<WorkoutResponseDto> updateWorkout(@PathVariable Long id,
                                                            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data for updating the workout", required = true)
                                                            @RequestBody @Valid WorkoutUpdateDto workoutUpdateDto,
                                                            Authentication authentication) {
        return new ResponseEntity<>(workoutService.update(id, workoutUpdateDto, authentication), HttpStatus.OK);
    }

    @Operation(summary = "Delete workout by ID", responses = {
            @ApiResponse(description = "Workout successfully deleted", responseCode = "204"),
            @ApiResponse(description = "Error deleting workout", responseCode = "400"),
            @ApiResponse(description = "Access denied", responseCode = "403"),
            @ApiResponse(description = "Workout not found", responseCode = "404")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id, Authentication authentication) {
        workoutService.delete(id, authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
