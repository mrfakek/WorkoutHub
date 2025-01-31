package by.tms.workouthub.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutResponseDto {
    private Long id;
    private String title;
    private LocalDateTime workoutDateTime;
    List<WorkoutExerciseDto> workoutExercises;
}
