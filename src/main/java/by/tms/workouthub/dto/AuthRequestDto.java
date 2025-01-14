package by.tms.workouthub.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthRequestDto {
    private String username;
    private String password;

}
