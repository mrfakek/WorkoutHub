package by.tms.workouthub.dto;

import by.tms.workouthub.entity.Anthropometry;
import by.tms.workouthub.entity.AnthropometryHistory;
import by.tms.workouthub.entity.Workout;
import by.tms.workouthub.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class AccountResponseDto {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private List<Workout> workouts = new ArrayList<>();
    private Anthropometry currentAnthropometry;
    private List<AnthropometryHistory> anthropometryHistory;
    private LocalDateTime createdAt;

}
