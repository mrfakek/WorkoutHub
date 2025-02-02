package by.tms.workouthub.enums;

import io.swagger.v3.oas.annotations.media.Schema;

public enum ExerciseType {
    @Schema(description = "Strength training exercises")
    CARDIO,

    @Schema(description = "Cardiovascular exercises")
    STRENGTH,

    @Schema(description = "Calisthenic exercises")
    CALISTHENIC,
}
