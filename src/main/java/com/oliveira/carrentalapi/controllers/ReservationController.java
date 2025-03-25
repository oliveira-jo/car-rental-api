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

import com.oliveira.carrentalapi.domain.dtos.ReservationRequestDto;
import com.oliveira.carrentalapi.domain.dtos.ReservationResponseDto;
import com.oliveira.carrentalapi.services.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
  @Operation(summary = "Save a reservation with all date is okay", method = "POST")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "422", description = "Invalid Dates!"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  public ResponseEntity<ReservationResponseDto> save(@RequestBody @Valid ReservationRequestDto request,
      @AuthenticationPrincipal UserDetails userDetails) {

    return ResponseEntity.ok().body(
        reservationService.save(request, userDetails));

  }

  @GetMapping
  @Operation(summary = "Return a list of all reservations saved in database ", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "422", description = "Invalid Dates!"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
    return ResponseEntity.ok().body(
        reservationService.getAllReservations());

  }

  @DeleteMapping(value = "/{id}")
  public ReservationResponseDto cancel(@PathVariable UUID id) {
    return null;
  }

}
