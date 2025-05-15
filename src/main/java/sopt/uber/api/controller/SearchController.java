package sopt.uber.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sopt.uber.api.dto.res.SearchKeywordListRes;
import sopt.uber.core.common.response.CommonResponse;
import sopt.uber.core.common.util.ResponseUtil;
import sopt.uber.core.service.SearchService;

@RestController
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/uber/search")
    public ResponseEntity<CommonResponse<Object>> getSearchList() {
        SearchKeywordListRes searchKeywordListRes = searchService.getSearchList();

        return ResponseUtil.success(searchKeywordListRes);
    }

    @DeleteMapping("uber/search{id}")
    public ResponseEntity<CommonResponse<Object>> deleteSearch(@PathVariable Long id) {
        searchService.deleteSearch(id);

        return ResponseUtil.success(null);
    }
}
