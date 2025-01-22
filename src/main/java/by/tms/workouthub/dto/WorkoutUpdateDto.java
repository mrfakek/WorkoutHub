package by.tms.workouthub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkoutUpdateDto {
    private int workoutId;
    private String workoutName;
}
