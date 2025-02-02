package by.tms.workouthub.controller;

import by.tms.workouthub.dto.ExerciseCreateDto;
import by.tms.workouthub.dto.ExerciseResponseDto;
import by.tms.workouthub.dto.ExerciseUpdateDto;
import by.tms.workouthub.service.ExerciseService;
import by.tms.workouthub.validation.annotation.ValidateThis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

private final ExerciseService exerciseService;

public ExerciseController(ExerciseService exerciseService) {
    this.exerciseService = exerciseService;

}

    @Operation(summary = "Create a new exercise", responses = {
            @ApiResponse(description = "Exercise successfully created", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseResponseDto.class))),
            @ApiResponse(description = "Bad request", responseCode = "400"),
            @ApiResponse(description = "Access denied", responseCode = "403")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @PostMapping
    public ResponseEntity<ExerciseResponseDto> createExercise(  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data for creating a new exercise", required = true)
                                                                    @RequestBody @Valid ExerciseCreateDto exerciseCreateDto) {
        return new ResponseEntity<>(exerciseService.create(exerciseCreateDto), HttpStatus.OK);
    }

    @Operation(summary = "Get exercise by ID", responses = {
            @ApiResponse(description = "Exercise found", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseResponseDto.class))),
            @ApiResponse(description = "Exercise not found", responseCode = "404")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> getExerciseById(@PathVariable Long id) {
    return new ResponseEntity<>(exerciseService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all exercises", responses = {
            @ApiResponse(description = "List of exercises", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<ExerciseResponseDto>> getAllExercises() {
    return new ResponseEntity<>(exerciseService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Update an exercise", responses = {
            @ApiResponse(description = "Exercise successfully updated", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseResponseDto.class))),
            @ApiResponse(description = "Bad request", responseCode = "400"),
            @ApiResponse(description = "Access denied", responseCode = "403"),
            @ApiResponse(description = "Exercise not found", responseCode = "404")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @ValidateThis
    @PatchMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> updateExercise(@PathVariable Long id,
                                                              @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data for updating an exercise", required = true)
                                                              @RequestBody @Valid ExerciseUpdateDto exerciseUpdateDto) {
    return new ResponseEntity<>(exerciseService.update(id, exerciseUpdateDto), HttpStatus.OK);
}

    @Operation(summary = "Delete an exercise", responses = {
            @ApiResponse(description = "Exercise successfully deleted", responseCode = "204"),
            @ApiResponse(description = "Error deleting exercise", responseCode = "400"),
            @ApiResponse(description = "Access denied", responseCode = "403"),
            @ApiResponse(description = "Exercise not found", responseCode = "404")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
    exerciseService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
