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
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account owner;
    private double height;
    private double weight;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
