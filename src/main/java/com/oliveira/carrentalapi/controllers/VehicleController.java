package com.oliveira.carrentalapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.carrentalapi.domain.dtos.VehicleRequestDto;
import com.oliveira.carrentalapi.domain.dtos.VehicleResponseDto;
import com.oliveira.carrentalapi.services.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/vehicle", produces = { "application/json" })
@Tag(name = "VehicleController")
public class VehicleController {

  private final VehicleService vehicleService;

  public VehicleController(VehicleService vehicleService) {

    this.vehicleService = vehicleService;

  }

  @Operation(summary = "Save a vehicle with all date is okay", method = "POST")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<VehicleResponseDto> save(@RequestBody @Valid VehicleRequestDto vehicleData) {

    var newVehicle = vehicleService.save(vehicleData);

    return ResponseEntity.ok().body(newVehicle);

  }

  @Operation(summary = "Update a vehicle by id with all date is okay", method = "PUT")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<VehicleResponseDto> update(@PathVariable UUID id,
      @RequestBody @Valid VehicleRequestDto vehicleData) {

    return ResponseEntity.ok().body(
        this.vehicleService.update(id, vehicleData));

  }

  @Operation(summary = "Delete a vehicle by", method = "DELETE")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {

    this.vehicleService.delete(id);

    return ResponseEntity.ok().build();

  }

  @Operation(summary = "Get vehicle by a provide id", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @GetMapping(value = "/{id}")
  public ResponseEntity<VehicleResponseDto> findById(@PathVariable UUID id) {

    return ResponseEntity.ok().body(
        this.vehicleService.findById(id));

  }

  @Operation(summary = "Get all vehicle", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @GetMapping()
  public ResponseEntity<List<VehicleResponseDto>> getAll() {

    return ResponseEntity.ok().body(
        this.vehicleService.getAll());

  }

}
