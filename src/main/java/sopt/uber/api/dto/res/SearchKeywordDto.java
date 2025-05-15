package sopt.uber.api.dto.res;

import java.time.LocalDateTime;

public record SearchKeywordDto(
        Long id,
        String location,
        String address,
        LocalDateTime date
){}
