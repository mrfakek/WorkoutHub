package by.tms.workouthub.entity;

import by.tms.workouthub.enums.Sex;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@Table(name = "anthropometrics")
public class Anthropometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;
    private Integer age;
    private Double height;
    private Double bodyweight;
    private Double chestCircumference;
    private Double waistCircumference;
    private Double bicepsCircumference;
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
