package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.request.ReservationRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.ReservationResponseDto;

public interface ReservationService {

  public ReservationResponseDto save(ReservationRequestDto reservationRequestDto, UUID id);

  public List<ReservationResponseDto> getAll();

  public ReservationResponseDto findById(UUID id);

  public ReservationResponseDto cancel(UUID reservationId, UUID userId);

}
