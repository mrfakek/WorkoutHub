package by.tms.workouthub.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublicAccountDto {
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
}
