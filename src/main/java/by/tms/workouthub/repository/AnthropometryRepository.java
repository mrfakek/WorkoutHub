package by.tms.workouthub.repository;

import by.tms.workouthub.entity.Account;
import by.tms.workouthub.entity.Anthropometry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnthropometryRepository extends JpaRepository<Anthropometry, Long> {
}
