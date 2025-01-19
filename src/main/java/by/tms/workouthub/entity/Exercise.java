package by.tms.workouthub.entity;

import by.tms.workouthub.enums.ExerciseType;
import by.tms.workouthub.enums.MuscleGroup;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.EnumMap;
import java.util.Map;

@Entity
@Data
@ToString
@Table(name = "exercises")

public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExerciseType exerciseType;
    @ElementCollection
    @CollectionTable(name = "exercise_muscle_usage", joinColumns = {@JoinColumn(name = "exercise_id")})
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "muscle_group")
    @Column(name = "usage_percentage")
    private Map<MuscleGroup, Integer> muscleUsagePercentages = new EnumMap<>(MuscleGroup.class);
}
