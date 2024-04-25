package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.VehicleDto;

public interface VehicleService {

  public VehicleDto save(VehicleDto vehicleData);

  public VehicleDto update(UUID id, VehicleDto vehicleData);

  public List<VehicleDto> getAllVehicles();

  public VehicleDto findById(UUID id);

  public void delete(UUID id);

}
