package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.VehicleDto;
import com.oliveira.carrentalapi.domain.dtos.VehicleResponseDto;

public interface VehicleService {

  public VehicleResponseDto save(VehicleDto vehicleData);

  public VehicleDto update(UUID id, VehicleDto vehicleData);

  public List<VehicleResponseDto> getAllVehicles();

  public VehicleDto findById(UUID id);

  public void delete(UUID id);

}
