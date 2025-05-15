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

        if (req.departures() == null || req.destination() == null) {
            throw new BusinessException(ErrorCode.INVALID_LOCATION);
        }

        if (req.departures().equals(req.destination())) {
            throw new BusinessException(ErrorCode.SAME_LOCATION);
        }

        Uber uber = new Uber(req.departures(), req.destination());
        uberRepository.save(uber);
    }
}
