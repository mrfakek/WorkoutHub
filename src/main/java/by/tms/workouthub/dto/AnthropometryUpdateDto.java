package by.tms.workouthub.dto;
import by.tms.workouthub.enums.Sex;
import by.tms.workouthub.validation.annotation.NonNullFieldValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NonNullFieldValidation
@Schema(description = "DTO for updating anthropometry data")
public class AnthropometryUpdateDto {
    @Schema(description = "Sex of the user", example = "MALE")
    private Sex sex;

    @Schema(description = "Birthdate of the user", example = "2001-03-15")
    @Past(message = "Birthday must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Schema(description = "Height of the user in centimeters", example = "185.5")
    @Positive(message = "Height must be positive")
    private Double height;

    @Schema(description = "Body weight of the user in kilograms", example = "75.0")
    @Positive(message = "Body weight must be positive")
    private Double bodyweight;

    @Schema(description = "Chest circumference of the user in centimeters", example = "98.0")
    @Positive(message = "Chest circumference must be positive")
    private Double chestCircumference;

    @Schema(description = "Waist circumference of the user in centimeters", example = "83.0")
    @Positive(message = "Waist circumference must be positive")
    private Double waistCircumference;

    @Schema(description = "Biceps circumference of the user in centimeters", example = "35.5")
    @Positive(message = "Biceps circumference must be positive")
    private Double bicepsCircumference;

    @Schema(description = "Forearm circumference of the user in centimeters", example = "30.2")
    @Positive(message = "Forearm circumference must be positive")
    private Double forearmCircumference;

    @Schema(description = "Hip circumference of the user in centimeters", example = "93.0")
    @Positive(message = "Hip circumference must be positive")
    private Double hipCircumference;

    @Schema(description = "Thigh circumference of the user in centimeters", example = "59.0")
    @Positive(message = "Thigh circumference must be positive")
    private Double thighCircumference;

    @Schema(description = "Calf circumference of the user in centimeters", example = "40.2")
    @Positive(message = "Calf circumference must be positive")
    private Double calfCircumference;
}
