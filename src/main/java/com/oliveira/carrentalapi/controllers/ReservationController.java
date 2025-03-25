package com.oliveira.carrentalapi.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.carrentalapi.domain.dtos.ReservationRequestDto;
import com.oliveira.carrentalapi.domain.dtos.ReservationResponseDto;
import com.oliveira.carrentalapi.services.ReservationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/reservation", produces = { "application/json" })
@Tag(name = "ReservationController")
public class ReservationController {

  private ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;

  }

  @PostMapping
  public ResponseEntity<ReservationResponseDto> save(@RequestBody @Valid ReservationRequestDto request,
      @AuthenticationPrincipal UserDetails userDetails) {

    return ResponseEntity.ok().body(
        reservationService.save(request, userDetails));

  }

  @DeleteMapping(value = "/{id}")
  public ReservationResponseDto cancel(@PathVariable UUID id) {
    return null;
  }

}
