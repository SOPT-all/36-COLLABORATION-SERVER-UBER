package sopt.uber.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.uber.api.dto.res.TaxiListsRes;
import sopt.uber.core.common.response.CommonResponse;
import sopt.uber.core.common.util.ResponseUtil;
import sopt.uber.core.service.TaxiService;

import java.util.List;
@RestController
@RequestMapping("/uber")
public class TaxiController {

    private final TaxiService taxiService;

    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @GetMapping("/v1/taxi")
    public ResponseEntity<CommonResponse<TaxiListsRes>> getTaxiLists() {
        TaxiListsRes taxiListsRes = taxiService.getTaxiLists();
        return ResponseUtil.success(taxiListsRes);
    }
}
