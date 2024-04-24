package com.oliveira.carrentalapi.domain.dtos;

public record VehicleDto(
    String model,
    String plate,
    String color,
    Boolean complete,
    Integer mileage,
    Boolean ative) {

}
