package by.tms.workouthub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO for creating a new workout")
public class WorkoutCreateDto {
    @Schema(description = "The title of the workout", example = "Morning Strength Workout")
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(description = "The date and time of the workout", example = "2025-01-02 10:30:00")
    @NotNull(message = "Workout date and time are required")
    @PastOrPresent(message = "Workout date and time must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workoutDateTime;

    @Schema(description = "List of exercises included in the workout", example = "[{\"exerciseId\":1, \"sets\":3, \"repetitions\":12}, {\"exerciseId\":2, \"sets\":4, \"repetitions\":10}]")
    @Size(min = 1, message = "At least one exercise is required")
    List<WorkoutExerciseDto> workoutExercises;
}
