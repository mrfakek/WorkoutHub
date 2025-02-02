package by.tms.workouthub.dto;

import by.tms.workouthub.validation.annotation.NonNullFieldValidation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NonNullFieldValidation
@Schema(description = "DTO for updating account information")
public class AccountUpdateDto {

    @Schema(description = "Email address of the user", example = "test@test.com")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "First name of the user", example = "Andrey")
    @Pattern(regexp = "^[A-Za-z'-]+$", message = "First name must contain only letters, hyphens, or apostrophes")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Fedotov")
    @Pattern(regexp = "^[A-Za-z'-]+$", message = " Last name must contain only letters, hyphens, or apostrophes")
    private String lastName;
}
