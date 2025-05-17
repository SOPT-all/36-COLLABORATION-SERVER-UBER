package sopt.uber.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.uber.api.dto.req.UberReq;
import sopt.uber.api.exception.BusinessException;
import sopt.uber.api.exception.ErrorCode;
import sopt.uber.core.domain.Uber;
import sopt.uber.core.repository.UberRepository;

@Service
@Transactional
public class UberService {

    private final UberRepository uberRepository;

    public UberService(UberRepository uberRepository) {
        this.uberRepository = uberRepository;
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

        Uber uber = new Uber(departures, destination);
        uberRepository.save(uber);
    }

    public void deleteSearch(Long id) {
        Uber uber = uberRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_ID));
        uberRepository.delete(uber);
    }

    public void deleteAllSearch() {
        uberRepository.deleteAll();
    }
}
