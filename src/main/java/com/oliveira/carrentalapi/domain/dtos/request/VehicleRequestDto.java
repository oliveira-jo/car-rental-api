package com.oliveira.carrentalapi.domain.dtos.request;

import jakarta.validation.constraints.NotNull;

public record VehicleRequestDto(
                @NotNull String model,
                @NotNull String imgUrl,
                @NotNull String plate,
                @NotNull String color,
                @NotNull Boolean complete,
                @NotNull Integer mileage,
                @NotNull Boolean ative,
                @NotNull String categoryId) {
}
