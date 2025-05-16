package sopt.uber.core.service;

import org.springframework.stereotype.Service;
import sopt.uber.api.dto.res.TaxiListsRes;
import sopt.uber.api.dto.res.TaxiRes;
import sopt.uber.core.repository.TaxiRepository;

import java.util.List;

@Service
public class TaxiService {

    private final TaxiRepository taxiRepository;

    public TaxiService(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public TaxiListsRes getTaxiLists() {
        List<TaxiRes> taxiList = taxiRepository.findAllTaxiListResponse();
        List<TaxiRes> caseTaxiList = taxiRepository.findAllCaseTaxiListResponse();

        return new TaxiListsRes(taxiList, caseTaxiList);
    }
}