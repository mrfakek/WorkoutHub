package by.tms.workouthub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "anthropometrics")
public class Anthropometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;
    private Integer age;
    private Double height;
    private Double bodyweight;
    private Double chestCircumference;
    private Double waistCircumference;
    private Double bicepCircumference;
    private Double forearmCircumference;
    private Double hipCircumference;
    private Double thighCircumference;
    private Double calfCircumference;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @PostLoad
    public void calculateAge(){
        if (birthday != null && createdAt != null) {
            this.age = createdAt.toLocalDate().getYear() - birthday.getYear();
        }
    }
}
