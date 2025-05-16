package sopt.uber.api.dto.res;

import java.util.List;

public record TaxiListsRes(
        List<TaxiRes> taxiList,
        List<TaxiRes> caseTaxiList
) {}