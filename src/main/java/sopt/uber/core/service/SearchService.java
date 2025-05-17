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
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Set<String> keywordsAndAddresses = searchList.stream()
                .flatMap(search -> Stream.of(search.getKeyword(), search.getAddress()))
                .collect(Collectors.toSet());

        List<Uber> ubers = uberRepository.findByDestinationIn(keywordsAndAddresses);

        Map<String, Uber> destinationToUber = ubers.stream()
                .collect(Collectors.toMap(Uber::getDestination, Function.identity()));

        List<SearchKeywordStringDto> mappedList = searchList.stream()
                .flatMap(search -> matchUber(destinationToUber, search)
                        .map(dto -> Stream.of(SearchKeywordStringDto.from(dto)))
                        .orElseGet(Stream::empty))
                .toList();

        return SearchKeywordListRes.of(mappedList);
    }

    private Optional<SearchKeywordDto> matchUber(Map<String, Uber> destinationToUber, Search search) {
        return Optional.ofNullable(destinationToUber.get(search.getKeyword()))
                .or(() -> Optional.ofNullable(destinationToUber.get(search.getAddress())))
                .map(uber -> new SearchKeywordDto(
                        search.getId(),
                        uber.getDestination(),
                        uber.getDepartures(),
                        uber.getDate()
                ));
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
