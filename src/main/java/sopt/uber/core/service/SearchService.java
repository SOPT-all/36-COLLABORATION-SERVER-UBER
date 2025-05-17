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

        Map<String, List<Uber>> destinationToUbers = ubers.stream()
                .collect(Collectors.groupingBy(Uber::getDestination));

        List<SearchKeywordStringDto> result = new ArrayList<>();

        for (Search search : searchList) {
            String keyword = search.getKeyword();
            String address = search.getAddress();

            List<Uber> keywordMatched = destinationToUbers.getOrDefault(keyword, List.of());
            for (Uber uber : keywordMatched) {
                SearchKeywordDto skDto = new SearchKeywordDto(
                        uber.getId(),
                        uber.getDestination(),
                        address,
                        uber.getDate()
                );
                result.add(SearchKeywordStringDto.from(skDto));
            }

            List<Uber> addressMatched = destinationToUbers.getOrDefault(address, List.of());
            for (Uber uber : addressMatched) {
                SearchKeywordDto skDto = new SearchKeywordDto(
                        uber.getId(),
                        uber.getDestination(),
                        keyword,
                        uber.getDate()
                );
                result.add(SearchKeywordStringDto.from(skDto));
            }
        }

        result.sort((o1, o2) -> o2.id().compareTo(o1.id()));

        return SearchKeywordListRes.of(result);
    }

    private Optional<SearchKeywordDto> matchUber(Map<String, Uber> destinationToUber, Search search) {
        return Optional.ofNullable(destinationToUber.get(search.getKeyword()))
                .or(() -> Optional.ofNullable(destinationToUber.get(search.getAddress())))
                .map(uber -> new SearchKeywordDto(
                        uber.getId(),
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
