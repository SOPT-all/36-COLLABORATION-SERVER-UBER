package sopt.uber.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.uber.api.dto.req.UberReq;
import sopt.uber.api.exception.BusinessException;
import sopt.uber.api.exception.ErrorCode;
import sopt.uber.core.domain.Uber;
import sopt.uber.core.repository.SearchRepository;
import sopt.uber.core.repository.UberRepository;

@Service
@Transactional
public class UberService {

    private final UberRepository uberRepository;
    private final SearchRepository searchRepository;

    public UberService(UberRepository uberRepository, SearchRepository searchRepository) {
        this.uberRepository = uberRepository;
        this.searchRepository = searchRepository;
    }

    public void createUber(UberReq req) {
        String departures = req.departures() != null ? req.departures().trim() : null;
        String destination = req.destination() != null ? req.destination().trim() : null;

        if (departures == null || departures.isBlank() || destination == null || destination.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_LOCATION);
        }

        if (departures.equals(destination)) {
            throw new BusinessException(ErrorCode.SAME_LOCATION);
        }

        // 출발지와 도착지가 모두 Search 테이블에 존재하는지 확인
        if (!searchRepository.existsByLocation(departures) || !searchRepository.existsByLocation(destination)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_LOCATION);
        }

        Uber uber = new Uber(departures, destination);
        uberRepository.save(uber);
    }
}
