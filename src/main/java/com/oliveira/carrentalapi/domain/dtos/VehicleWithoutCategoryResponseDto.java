package com.oliveira.carrentalapi.domain.dtos;

import java.util.UUID;

public record VehicleWithoutCategoryResponseDto(
    UUID id,
    String model,
    String plate,
    String color,
    Boolean complete,
    Integer mileage,
    Boolean ative) {
}
