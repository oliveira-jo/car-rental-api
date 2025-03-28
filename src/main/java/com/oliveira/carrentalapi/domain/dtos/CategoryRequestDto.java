package com.oliveira.carrentalapi.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDto(
    @NotNull String categoryName,
    @NotNull String datails,
    @NotNull Integer numBigSuitCases,
    @NotNull Integer numSmallSuitCases,
    @NotNull Integer numOfPeople,
    @NotNull Boolean complete,
    @NotNull Float value) {
}
