package by.tms.workouthub.dto;

import by.tms.workouthub.validation.annotation.NonNullFieldValidation;
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
@NonNullFieldValidation
@Schema(description = "DTO for updating an existing workout")
public class WorkoutUpdateDto {

    @Schema(description = "The title of the workout", example = "Morning Cardio")
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(description = "The date and time when the workout takes place, must be in the past or present", example = "2025-01-02 08:00:00")
    @NotNull(message = "Workout date and time are required")
    @PastOrPresent(message = "Workout date and time must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workoutDateTime;

    @Schema(description = "The list of exercises included in the workout. At least one exercise is required. For cardio exercises, distance or duration should be specified.", example = "[{exerciseName: 'Running', durationInSeconds: 1800, distance: 5000}]")
    @Size(min = 1, message = "At least one exercise is required")
    List<WorkoutExerciseDto> workoutExercises;
}
