package by.tms.workouthub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO representing an exercise within a workout, including details like duration, weight, distance, repetitions, and sets.")
public class WorkoutExerciseDto {

    @Schema(description = "The name of the exercise", example = "Bench Press")
    @NotBlank(message = "Exercise name must not be blank")
    private String exerciseName;

    @Schema(description = "The duration of the exercise in seconds", example = "120")
    @NotNull(message = "Duration must not be null")
    @Min(value = 1, message = "Duration must be at least 1 second")
    private Integer durationInSeconds;

    @Schema(description = "The weight used for the exercise in kilograms", example = "100.5")
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0.0")
    private Double weight;

    @Schema(description = "The distance covered during the exercise in meters, applicable for distance-based exercises (e.g., running, cycling)", example = "5000.0")
    @DecimalMin(value = "0.0", inclusive = false, message = "Distance must be greater than 0.0")
    private Double distance;


    @Schema(description = "The number of repetitions performed in one set", example = "12")
    @Min(value = 1, message = "Repetitions must be at least 1")
    private Integer repetitions;

    @Schema(description = "The number of sets performed", example = "4")
    @Min(value = 1, message = "Sets must be at least 1")
    private Integer sets;

    @Schema(description = "The number of repetitions left in reserve at the end of a set", example = "2")
    @Min(value = 0, message = "Reps in reserve must be at least 0")
    private Integer repsInReserve;
}
