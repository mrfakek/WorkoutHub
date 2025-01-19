package by.tms.workouthub.dto;

import by.tms.workouthub.enums.ExerciseType;
import by.tms.workouthub.enums.MuscleGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.EnumMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExerciseResponseDto {
    private String name;
    private String description;
    private ExerciseType exerciseType;
    private Map<@NotNull MuscleGroup, @Min(1) @Max(100) Integer> muscleUsagePercentages = new EnumMap<>(MuscleGroup.class);
}
