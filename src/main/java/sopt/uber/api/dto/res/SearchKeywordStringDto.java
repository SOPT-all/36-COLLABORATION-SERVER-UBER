package sopt.uber.api.dto.res;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record SearchKeywordStringDto(
        Long id,
        String location,
        String address,
        String date
) {
    public static SearchKeywordStringDto from(SearchKeywordDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");
        return new SearchKeywordStringDto(
                dto.id(),
                dto.location(),
                dto.address(),
                dto.date().format(formatter)
        );
    }
}
