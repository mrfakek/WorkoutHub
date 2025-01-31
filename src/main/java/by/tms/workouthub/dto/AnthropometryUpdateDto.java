package by.tms.workouthub.dto;

import by.tms.workouthub.validation.annotation.NonNullFieldValidation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NonNullFieldValidation
public class AnthropometryUpdateDto {
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
