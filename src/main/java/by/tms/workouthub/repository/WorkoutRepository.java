package by.tms.workouthub.repository;

import by.tms.workouthub.entity.Account;
import by.tms.workouthub.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByOwner(Account owner);
}

