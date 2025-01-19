package by.tms.workouthub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ToString
public class AccountUpdateDto {
    private String email;
    private String firstName;
    private String lastName;
}
