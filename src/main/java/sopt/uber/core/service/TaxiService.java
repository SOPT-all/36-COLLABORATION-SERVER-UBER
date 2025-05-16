package sopt.uber.core.service;

import org.springframework.stereotype.Service;
import sopt.uber.api.dto.res.TaxiListRes;
import sopt.uber.api.dto.res.TaxiRes;
import sopt.uber.core.domain.Taxi;
import sopt.uber.core.repository.TaxiRepository;

import java.util.List;

@Service
public class TaxiService {

    private final TaxiRepository taxiRepository;

    public TaxiService(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public TaxiListRes getTaxiList() {
        List<TaxiRes> taxiList = taxiRepository.findAllTaxiListResponse();
        return new TaxiListRes(taxiList);
    }
}