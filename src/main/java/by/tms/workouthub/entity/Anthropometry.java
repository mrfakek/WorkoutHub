package by.tms.workouthub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "anthropometrics")
public class Anthropometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "currentAnthropometry")
    private Account owner;
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;
    private int age;
    private double height;
    private double weight;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
