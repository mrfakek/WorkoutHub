package by.tms.workouthub.dto;

import by.tms.workouthub.enums.ExerciseType;
import by.tms.workouthub.enums.MuscleGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.EnumMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponseDto {
    private Long id;
    private String name;
    private String description;
    private ExerciseType exerciseType;
    private Map<MuscleGroup,Integer> muscleUsagePercentages = new EnumMap<>(MuscleGroup.class);
}
