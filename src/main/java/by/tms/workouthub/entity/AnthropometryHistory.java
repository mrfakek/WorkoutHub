package by.tms.workouthub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "anthropometry_historys")
public class AnthropometryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account owner;
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
}
