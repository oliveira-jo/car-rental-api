package com.oliveira.carrentalapi.domain.dtos;

import java.util.UUID;

public record VehicleResponseDto(
                UUID id,
                String model,
                String plate,
                String color,
                Boolean complete,
                Integer mileage,
                Boolean ative,
                CategoryResponseDto category) {
}
