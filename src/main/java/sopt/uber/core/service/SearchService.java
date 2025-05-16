package sopt.uber.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.uber.api.dto.res.SearchKeywordDto;
import sopt.uber.api.dto.res.SearchKeywordListRes;
import sopt.uber.api.dto.res.SearchKeywordStringDto;
import sopt.uber.api.exception.BusinessException;
import sopt.uber.api.exception.ErrorCode;
import sopt.uber.core.domain.Search;
import sopt.uber.core.repository.SearchRepository;

import java.util.List;

@Service
@Transactional
public class SearchService {
    private final SearchRepository searchRepository;

    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Transactional(readOnly = true)
    public SearchKeywordListRes getSearchList() {
        List<SearchKeywordDto> searchList = searchRepository.findAllSearchKeywords();

        List<SearchKeywordStringDto> mappedList = searchList.stream()
                .map(SearchKeywordStringDto::from)
                .toList();
        return SearchKeywordListRes.of(mappedList);
    }

    @Transactional
    public void deleteAllSearch() {
        searchRepository.deleteAll();
    }
}
