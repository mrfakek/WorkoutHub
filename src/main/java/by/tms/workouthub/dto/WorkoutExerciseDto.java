package by.tms.workouthub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class WorkoutExerciseDto {
    @NotBlank(message = "Exercise name must not be blank")
    private String exerciseName;
    @NotNull(message = "Duration must not be null")
    @Min(value = 1, message = "Duration must be at least 1 second")
    private Integer durationInSeconds;
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0.0")
    private Double weight;
    @DecimalMin(value = "0.0", inclusive = false, message = "Distance must be greater than 0.0")
    private Double distance;
    @Min(value = 1, message = "Repetitions must be at least 1")
    private Integer repetitions;
    @Min(value = 1, message = "Sets must be at least 1")
    private Integer sets;
    @Min(value = 0, message = "Reps in reserve must be at least 0")
    private Integer repsInReserve;
}
