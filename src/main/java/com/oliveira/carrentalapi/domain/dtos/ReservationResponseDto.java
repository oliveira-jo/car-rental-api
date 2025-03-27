package com.oliveira.carrentalapi.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationResponseDto(
                UUID id,
                LocalDateTime pickUpDate,
                LocalDateTime returnDate,
                String user,
                String category,
                Integer qtdDays,
                Float dailyRentalValue,
                Float totalValue,
                String status) {
}
