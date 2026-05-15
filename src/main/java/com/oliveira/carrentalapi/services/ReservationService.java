package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import com.oliveira.carrentalapi.domain.dtos.request.ReservationRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.ReservationResponseDto;

public interface ReservationService {

  public ReservationResponseDto save(ReservationRequestDto reservationRequestDto, UUID id);

  public Page<ReservationResponseDto> getAll(Authentication auth, Pageable pageable);

  public ReservationResponseDto findById(Authentication auth, UUID id);

  public ReservationResponseDto cancel(UUID reservationId, UUID userId);

}
