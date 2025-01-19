package by.tms.workouthub.controller;

import by.tms.workouthub.dto.ExerciseCreateDto;
import by.tms.workouthub.dto.ExerciseDeleteDto;
import by.tms.workouthub.dto.ExerciseResponseDto;
import by.tms.workouthub.entity.Exercise;
import by.tms.workouthub.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ExerciseResponseDto> addExercise(@RequestBody ExerciseCreateDto exerciseCreateDto) {

        return new ResponseEntity<>(exerciseService.create(exerciseCreateDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDto>> getAllExercises() {
   return new ResponseEntity(exerciseService.getAll(), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ExerciseResponseDto> updateExercise(@RequestBody ExerciseCreateDto exerciseCreateDto) {
    return new ResponseEntity<>(exerciseService.update(exerciseCreateDto), HttpStatus.OK);
}
    @DeleteMapping
    public ResponseEntity<Void> deleteExercise(@RequestBody ExerciseDeleteDto exerciseDeleteDto) {
    exerciseService.delete(exerciseDeleteDto.getName());
    return new ResponseEntity<>(HttpStatus.OK);
    }
}
