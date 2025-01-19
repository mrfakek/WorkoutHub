package by.tms.workouthub.dto;

import by.tms.workouthub.enums.ExerciseType;
import by.tms.workouthub.enums.MuscleGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.EnumMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ExerciseCreateDto {

    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private ExerciseType exerciseType;

    @Valid
    private Map<@NotBlank MuscleGroup, @Min(1) @Max(100) Integer> muscleUsagePercentages = new EnumMap<>(MuscleGroup.class);
}
