package com.oliveira.carrentalapi.domain.dtos.request;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDto(
    @NotNull String categoryName,
    @NotNull String details,
    @NotNull Integer numSuitcase,
    @NotNull Integer numOfPeople,
    @NotNull Boolean complete,
    @NotNull Float value) {
}
