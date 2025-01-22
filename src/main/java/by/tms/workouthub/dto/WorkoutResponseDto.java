package by.tms.workouthub.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkoutResponseDto {
    private Long id;
    private String title;
    private LocalDateTime workoutDateTime;
    List<WorkoutExerciseDto> workoutExercises;
}
