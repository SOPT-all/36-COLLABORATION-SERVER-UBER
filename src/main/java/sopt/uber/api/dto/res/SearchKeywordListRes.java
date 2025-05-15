package sopt.uber.api.dto.res;

import java.util.List;

public record SearchKeywordListRes(
    List<SearchKeywordStringDto> searchKeywords
) {
    public static SearchKeywordListRes of(List<SearchKeywordStringDto> searchKeywords) {
        return new SearchKeywordListRes(searchKeywords);
    }
}
