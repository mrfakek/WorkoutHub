package by.tms.workouthub.dto;

import by.tms.workouthub.validation.annotation.NonNullFieldValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NonNullFieldValidation
public class AnthropometryCreateDto {
    @NotNull
    @Past(message = "Birthday must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Double height;
    private Double bodyweight;
    private Double chestCircumference;
    private Double waistCircumference;
    private Double bicepCircumference;
    private Double forearmCircumference;
    private Double hipCircumference;
    private Double thighCircumference;
    private Double calfCircumference;
}
