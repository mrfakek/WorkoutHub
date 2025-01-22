package by.tms.workouthub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "workout_exercises")
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private int durationInSeconds;
    private double weight;
    private int repetitions;
    private int sets;
    private int repsInReserve;
}
