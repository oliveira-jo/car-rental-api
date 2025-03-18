package com.oliveira.carrentalapi.domain.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record VehicleDto(
                UUID id,
                @NotBlank String model,
                @NotBlank String plate,
                @NotBlank String color,
                @NotBlank Boolean complete,
                @NotBlank Integer mileage,
                @NotBlank Boolean ative,
                @NotBlank String categoryId) {
}
