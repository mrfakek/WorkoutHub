package by.tms.workouthub.controller;

import by.tms.workouthub.dto.ExerciseCreateDto;
import by.tms.workouthub.dto.ExerciseResponseDto;
import by.tms.workouthub.dto.ExerciseUpdateDto;
import by.tms.workouthub.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

private final ExerciseService exerciseService;

public ExerciseController(ExerciseService exerciseService) {
    this.exerciseService = exerciseService;

}

    @PostMapping
    public ResponseEntity<ExerciseResponseDto> createExercise(@RequestBody ExerciseCreateDto exerciseCreateDto) {
        return new ResponseEntity<>(exerciseService.create(exerciseCreateDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> getExerciseById(@PathVariable long id) {
    return new ResponseEntity<>(exerciseService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDto>> getAllExercises() {
    return new ResponseEntity<>(exerciseService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ExerciseResponseDto> updateExercise(@RequestBody ExerciseUpdateDto exerciseUpdateDto) {
    return new ResponseEntity<>(exerciseService.update(exerciseUpdateDto), HttpStatus.OK);
}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
    exerciseService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
