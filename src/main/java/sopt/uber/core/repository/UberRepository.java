package sopt.uber.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.uber.core.domain.Uber;

@Repository
public interface UberRepository extends JpaRepository<Uber, Long> {
}
