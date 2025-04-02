package com.oliveira.carrentalapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.oliveira.carrentalapi.domain.dtos.request.ReservationRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.ReservationResponseDto;
import com.oliveira.carrentalapi.domain.models.User;
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
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  public ResponseEntity<ReservationResponseDto> save(@RequestBody @Valid ReservationRequestDto request,
      Authentication auth) {
    return ResponseEntity.ok().body(
        reservationService.save(request, ((User) auth.getPrincipal()).getId()));
  }

  @GetMapping(value = "/all")
  @Operation(summary = "Return all reservations for admin or support", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  public ResponseEntity<List<ReservationResponseDto>> getAllReservations(Authentication auth) {

    return ResponseEntity.ok().body(
        reservationService.getAll(auth));

  }

  @GetMapping(value = "/{id}")
  @Operation(summary = "Return a reservations by a provided UUID ", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  public ResponseEntity<ReservationResponseDto> getByID(@PathVariable UUID id) {
    return ResponseEntity.ok().body(
        reservationService.findById(id));
  }

  @Operation(summary = "Cancel a reservation by a provide UUID", method = "DELETE")
  @DeleteMapping(value = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  public ResponseEntity<ReservationResponseDto> cancel(@PathVariable UUID id,
      Authentication auth) {
    return ResponseEntity.ok().body(
        reservationService.cancel(id, ((User) auth.getPrincipal()).getId()));
  }

}
