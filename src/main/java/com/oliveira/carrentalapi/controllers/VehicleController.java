package com.oliveira.carrentalapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.carrentalapi.domain.dtos.VehicleDto;
import com.oliveira.carrentalapi.services.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

  private final VehicleService vehicleService;

  public VehicleController(VehicleService vehicleService) {

    this.vehicleService = vehicleService;

  }

  @PostMapping
  public ResponseEntity<VehicleDto> save(@RequestBody @Valid VehicleDto vehicleData) {

    var newVehicle = vehicleService.save(vehicleData);

    return ResponseEntity.ok().body(newVehicle);

  }

  @PutMapping("/{id}")
  public ResponseEntity<VehicleDto> update(@PathVariable("id") UUID id, @RequestBody @Valid VehicleDto vehicleData) {

    var updateVehicle = this.vehicleService.update(id, vehicleData);

    return ResponseEntity.ok().body(updateVehicle);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<VehicleDto> delete(@PathVariable("id") UUID id) {

    this.vehicleService.delete(id);

    return ResponseEntity.ok().build();

  }

  @GetMapping
  public ResponseEntity<List<VehicleDto>> getVehicles() {

    var vehicles = this.vehicleService.getAllVehicles();

    return ResponseEntity.ok().body(vehicles);

  }

}
