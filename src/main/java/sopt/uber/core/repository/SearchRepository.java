package sopt.uber.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sopt.uber.core.domain.Search;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {
    @Query("SELECT COUNT(s) > 0 FROM Search s WHERE s.address = :location OR s.keyword = :location")
    boolean existsByLocation(String location);
}

