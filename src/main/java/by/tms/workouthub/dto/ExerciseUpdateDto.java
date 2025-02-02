package by.tms.workouthub.dto;

import by.tms.workouthub.enums.ExerciseType;
import by.tms.workouthub.enums.MuscleGroup;
import by.tms.workouthub.validation.annotation.NonNullFieldValidation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.EnumMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NonNullFieldValidation
@Schema(description = "DTO for updating an existing exercise")
public class ExerciseUpdateDto {

    @Schema(description = "The name of the exercise", example = "French Bench Press")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "A brief description of the exercise", example = "An upper body exercise primarily targeting the triceps")
    private String description;

    @Schema(description = "The type of exercise", example = "STRENGTH", allowableValues = {"STRENGTH", "CARDIO", "CALISTHENIC"})
    @NotBlank(message = "Exercise type is required")
    private ExerciseType exerciseType;

    @Schema(description = "Muscle groups and their usage percentage in the exercise", example = "{CHEST=30, TRICEPS=50, SHOULDERS=20}")
    @Valid
    private Map<@NotBlank MuscleGroup, @Min(1) @Max(100) Integer> muscleUsagePercentages = new EnumMap<>(MuscleGroup.class);
}
