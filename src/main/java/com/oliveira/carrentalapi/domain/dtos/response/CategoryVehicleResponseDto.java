package com.oliveira.carrentalapi.domain.dtos.response;

import java.util.UUID;
import java.util.List;

public record CategoryVehicleResponseDto(
        UUID id,
        String categoryName,
        String details,
        Integer numSuitcase,
        Integer numOfPeople,
        Boolean complete,
        Float value,
        List<VehicleWithoutCategoryResponseDto> vehicles) {
}
