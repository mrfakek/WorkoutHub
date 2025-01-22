package by.tms.workouthub.entity;

import jakarta.persistence.*;
import lombok.Data;
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
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime workoutDateTime;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workout")
    @ToString.Exclude
    List<WorkoutExercise> workoutExercises = new ArrayList<>();

}
