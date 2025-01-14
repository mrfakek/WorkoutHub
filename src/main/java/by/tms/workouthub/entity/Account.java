package by.tms.workouthub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@ToString
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String email;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_anthropometry_id")
    private Anthropometry currentAnthropometry;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<AnthropometryHistory> anthropometryHistory;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}