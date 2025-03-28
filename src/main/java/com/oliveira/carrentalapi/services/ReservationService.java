package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.oliveira.carrentalapi.domain.dtos.request.ReservationRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.ReservationResponseDto;

public interface ReservationService {

  public ReservationResponseDto save(ReservationRequestDto reservationRequestDto, UserDetails userLogged);

  public List<ReservationResponseDto> getAll();

  public ReservationResponseDto findById(UUID id);

  public ReservationResponseDto cancel(UUID id, UserDetails userLogged);

}
