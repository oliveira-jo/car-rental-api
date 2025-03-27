package com.oliveira.carrentalapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.oliveira.carrentalapi.domain.dtos.ReservationRequestDto;
import com.oliveira.carrentalapi.domain.dtos.ReservationResponseDto;
import com.oliveira.carrentalapi.services.ReservationService;

@RestController
@RequestMapping(value = "/reservation", produces = { "application/json" })
@Tag(name = "ReservationController")
public class ReservationController {

  private ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;

  }

  @PostMapping
  @Operation(summary = "Save a reservation with all date is okay", method = "POST")
  public ResponseEntity<ReservationResponseDto> save(@RequestBody @Valid ReservationRequestDto request,
      @AuthenticationPrincipal UserDetails userDetails) {

    return ResponseEntity.ok().body(
        reservationService.save(request, userDetails));

  }

  @GetMapping
  @Operation(summary = "Return a list of all reservations saved in database ", method = "GET")
  public ResponseEntity<List<ReservationResponseDto>> getAll() {
    return ResponseEntity.ok().body(
        reservationService.getAll());

  }

  @GetMapping(value = "/{id}")
  @Operation(summary = "Return a reservations by a provided UUID ", method = "GET")
  public ResponseEntity<ReservationResponseDto> getByID(@PathVariable UUID id) {
    return ResponseEntity.ok().body(
        reservationService.getByID(id));

  }

  @Operation(summary = "Cancel a reservation by a provide UUID", method = "DELETE")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ReservationResponseDto> cancel(@PathVariable UUID id,
      @AuthenticationPrincipal UserDetails userDetails) {
    return ResponseEntity.ok().body(
        reservationService.cancel(id, userDetails));
  }

}
