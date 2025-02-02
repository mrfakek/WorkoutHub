package by.tms.workouthub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO for creating a new account")
public class AccountCreateDto {

    @NotBlank(message = "Name is required")
    @Schema(description = "The username for the account", example = "Nikita")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Your password must be at least 8 characters long and include at least one uppercase letter," +
                    " one lowercase letter, one number, and one special character (@$!%*?&)")
    @Schema(description = "The password for the account, must meet complexity requirements", example = "Password123!")
    private String password;
}
