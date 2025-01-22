package by.tms.workouthub.repository;

import by.tms.workouthub.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}

