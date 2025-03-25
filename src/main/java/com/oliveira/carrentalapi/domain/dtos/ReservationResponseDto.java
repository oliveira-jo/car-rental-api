package com.oliveira.carrentalapi.domain.dtos;

import java.time.LocalDateTime;

public record ReservationResponseDto(
    LocalDateTime pickUpDate,
    LocalDateTime returnDate,
    String user,
    String category,
    Integer qtdDays,
    Float dailyRentalValue,
    Float totalValue) {
}
