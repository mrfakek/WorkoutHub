package by.tms.workouthub.entity;

import by.tms.workouthub.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
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
    @Enumerated(EnumType.STRING)
    private Role role;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
    private List<Workout> workouts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "current_anthropometry_id")
    private Anthropometry currentAnthropometry;
    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnthropometryHistory> anthropometryHistory = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

}