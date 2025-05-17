package sopt.uber.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sopt.uber.api.dto.res.SearchKeywordListRes;
import sopt.uber.core.common.response.CommonResponse;
import sopt.uber.core.common.util.ResponseUtil;
import sopt.uber.core.service.SearchService;

@RestController
@RequestMapping("/uber/v1")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponse<SearchKeywordListRes>> getSearchList() {
        SearchKeywordListRes searchKeywordListRes = searchService.getSearchList();

        return ResponseUtil.success(searchKeywordListRes);
    }

/*    @DeleteMapping("/search/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteSearch(@PathVariable Long id) {
        searchService.deleteSearch(id);

        return ResponseUtil.success(null);
    }

    @DeleteMapping("/search")
    public ResponseEntity<CommonResponse<Void>> deleteAllSearch() {
        searchService.deleteAllSearch();

        return ResponseUtil.success(null);
    }*/
}