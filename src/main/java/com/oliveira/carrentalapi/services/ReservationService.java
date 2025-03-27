package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.oliveira.carrentalapi.domain.dtos.ReservationRequestDto;
import com.oliveira.carrentalapi.domain.dtos.ReservationResponseDto;

public interface ReservationService {

  public ReservationResponseDto save(ReservationRequestDto reservationRequestDto, UserDetails userLogged);

  public List<ReservationResponseDto> getAll();

  public ReservationResponseDto getByID(UUID id);

  public ReservationResponseDto cancel(UUID id, UserDetails userLogged);

}
