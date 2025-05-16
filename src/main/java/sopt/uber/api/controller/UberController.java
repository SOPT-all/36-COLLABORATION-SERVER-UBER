package sopt.uber.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.uber.api.dto.req.UberReq;
import sopt.uber.core.common.response.CommonResponse;
import sopt.uber.core.common.util.ResponseUtil;
import sopt.uber.core.service.UberService;

@RestController
@RequestMapping("/uber")
public class UberController {

    private final UberService uberService;

    public UberController(UberService uberService) {
        this.uberService = uberService;
    }

    @PostMapping("/v1/location")
    public ResponseEntity<CommonResponse<Void>> createUber(@RequestBody UberReq req) {
        uberService.createUber(req);
        return ResponseUtil.success(null);
    }
}
