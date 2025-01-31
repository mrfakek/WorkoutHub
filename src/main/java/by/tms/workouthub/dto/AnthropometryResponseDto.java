package by.tms.workouthub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnthropometryResponseDto {
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
