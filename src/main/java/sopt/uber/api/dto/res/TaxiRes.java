package sopt.uber.api.dto.res;

public record TaxiRes(
        long id,
        String type,
        int min,
        int max,
        int guests,
        String comment,

        String image
) {}