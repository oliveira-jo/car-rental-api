package com.oliveira.carrentalapi.domain.dtos.response;

import java.util.UUID;

public record VehicleResponseDto(
        UUID id,
        String model,
        String imgUrl,
        String plate,
        String color,
        Boolean complete,
        Integer mileage,
        Boolean ative,
        CategoryResponseDto category) {
}
