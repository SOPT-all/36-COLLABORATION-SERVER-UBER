package sopt.uber.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sopt.uber.api.dto.res.SearchKeywordDto;
import sopt.uber.core.domain.Search;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {
    @Query("SELECT new sopt.uber.api.dto.res.SearchKeywordDto(s.id, s.keyword, s.address, s.date) FROM Search s")
    List<SearchKeywordDto> findAllSearchKeywords();
}
