package sopt.uber.api.dto.res;

import java.time.format.DateTimeFormatter;

public record SearchKeywordStringDto(
        Long id,
        String location,
        String address,
        String date
) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM.dd");

    public static SearchKeywordStringDto from(SearchKeywordDto dto) {
        return new SearchKeywordStringDto(
                dto.id(),
                dto.location(),
                dto.address(),
                dto.date().format(FORMATTER)
        );
    }
}
