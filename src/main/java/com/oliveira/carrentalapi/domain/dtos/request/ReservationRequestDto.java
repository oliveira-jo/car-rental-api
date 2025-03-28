package com.oliveira.carrentalapi.domain.dtos.request;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record ReservationRequestDto(
    @NotNull LocalDateTime pickUpDate,
    @NotNull LocalDateTime returnDate,
    @NotNull UUID groupID) {
}
