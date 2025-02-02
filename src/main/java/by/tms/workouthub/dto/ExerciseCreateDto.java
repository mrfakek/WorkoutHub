package by.tms.workouthub.dto;

import by.tms.workouthub.enums.ExerciseType;
import by.tms.workouthub.enums.MuscleGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.EnumMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO for creating a new exercise")
public class ExerciseCreateDto {

    @Schema(description = "The name of the exercise", example = "Bench Press")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "A brief description of the exercise", example = "A basic chest exercise targeting the pectorals")
    private String description;

    @NotNull(message = "Exercise type is required")
    @Schema(description = "The type of exercise", example = "STRENGTH", allowableValues = {"STRENGTH", "CARDIO", "CALISTHENIC"})
    private ExerciseType exerciseType;

    @Schema(description = "Muscle groups and their usage percentage in the exercise", example = "{CHEST=70, TRICEPS=20, SHOULDERS=10}")
    @Valid
    private Map<@NotNull MuscleGroup, @Min(1) @Max(100) Integer>  muscleUsagePercentages = new EnumMap<>(MuscleGroup.class);
}
