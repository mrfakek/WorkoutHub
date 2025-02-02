package by.tms.workouthub.repository;

import by.tms.workouthub.entity.Account;
import by.tms.workouthub.entity.AnthropometryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnthropometryHistoryRepository extends JpaRepository<AnthropometryHistory, Long> {
    List<AnthropometryHistory> findByOwner(Account owner);
    Optional<AnthropometryHistory> findTopByOwnerOrderByCreatedAtDesc(Account owner);
}
