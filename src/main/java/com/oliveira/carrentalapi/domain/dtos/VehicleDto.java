package com.oliveira.carrentalapi.domain.dtos;

import com.oliveira.carrentalapi.domain.models.Category;

public record VehicleDto(
                String model,
                String plate,
                String color,
                Boolean complete,
                Integer mileage,
                Boolean ative,
                Category category) {
}
