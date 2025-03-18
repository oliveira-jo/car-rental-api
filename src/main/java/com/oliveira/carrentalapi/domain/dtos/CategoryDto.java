package com.oliveira.carrentalapi.domain.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(
        UUID id,
        @NotBlank String categoryName,
        @NotBlank String datails,
        @NotBlank Integer numBigSuitCases,
        @NotBlank Integer numSmallSuitCases,
        @NotBlank Integer numOfPeople,
        @NotBlank Boolean complete,
        @NotBlank Float value) {

}
