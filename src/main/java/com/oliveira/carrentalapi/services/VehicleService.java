package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.VehicleDto;
import com.oliveira.carrentalapi.domain.models.Vehicle;

public interface VehicleService {

  public Vehicle save(VehicleDto vehicleData);

  public Vehicle update(UUID id, VehicleDto vehicleData);

  public List<Vehicle> getAllVehicles();

  public Vehicle findById(UUID id);

  public void delete(UUID id);

}
