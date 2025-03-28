package com.oliveira.carrentalapi.domain.dtos;

import java.util.UUID;
import java.util.List;

public record CategoryVehicleResponseDto(
    UUID id,
    String categoryName,
    String datails,
    Integer numBigSuitCases,
    Integer numSmallSuitCases,
    Integer numOfPeople,
    Boolean complete,
    Float value,
    List<VehicleResponseDto> vehicles) {
}
