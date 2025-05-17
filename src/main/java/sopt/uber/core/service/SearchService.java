package sopt.uber.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.uber.api.dto.res.SearchKeywordDto;
import sopt.uber.api.dto.res.SearchKeywordListRes;
import sopt.uber.api.dto.res.SearchKeywordStringDto;
import sopt.uber.api.exception.BusinessException;
import sopt.uber.api.exception.ErrorCode;
import sopt.uber.core.domain.Search;
import sopt.uber.core.domain.Uber;
import sopt.uber.core.repository.SearchRepository;
import sopt.uber.core.repository.UberRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class SearchService {
    private final SearchRepository searchRepository;
    private final UberRepository uberRepository;

    public SearchService(SearchRepository searchRepository, UberRepository uberRepository) {
        this.uberRepository = uberRepository;
        this.searchRepository = searchRepository;
    }
    @Transactional(readOnly = true)
    public SearchKeywordListRes getSearchList() {
        List<Search> searchList = searchRepository.findAll();

        List<SearchKeywordStringDto> mappedList = searchList.stream()
                .map(search -> {
                    String keyword = search.getKeyword();
                    String address = search.getAddress();

                    Optional<Uber> matchedByKeyword = uberRepository.findByDestination(keyword);
                    if (matchedByKeyword.isPresent()) {
                        Uber uber = matchedByKeyword.get();
                        return SearchKeywordStringDto.from(new SearchKeywordDto(
                                search.getId(),
                                uber.getDestination(),
                                uber.getDepartures(),
                                uber.getDate()
                        ));
                    }

                    Optional<Uber> matchedByAddress = uberRepository.findByDestination(address);
                    if (matchedByAddress.isPresent()) {
                        Uber uber = matchedByAddress.get();
                        return SearchKeywordStringDto.from(new SearchKeywordDto(
                                search.getId(),
                                uber.getDestination(),
                                uber.getDepartures(),
                                uber.getDate()
                        ));
                    }

                    return null;

                })
                .filter(Objects::nonNull)
                .toList();

        return SearchKeywordListRes.of(mappedList);
    }

    @Transactional
    public void deleteSearch(Long id) {
        Search search = searchRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_ID));
        searchRepository.delete(search);
    }

    @Transactional
    public void deleteAllSearch() {
        searchRepository.deleteAll();
    }
}
