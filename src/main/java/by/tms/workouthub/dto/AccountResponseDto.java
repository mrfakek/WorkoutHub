package by.tms.workouthub.dto;

import by.tms.workouthub.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponseDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private List<WorkoutResponseDto> workouts = new ArrayList<>();
    private AnthropometryResponseDto currentAnthropometry;
    private List<AnthropometryResponseDto> anthropometryHistory;
    private LocalDateTime createdAt;
}
