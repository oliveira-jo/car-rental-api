package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.request.VehicleRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.VehicleResponseDto;

public interface VehicleService {

  public VehicleResponseDto save(VehicleRequestDto vehicleData);

  public VehicleResponseDto update(UUID id, VehicleRequestDto vehicleData);

  public List<VehicleResponseDto> getAll();

  public VehicleResponseDto findById(UUID id);

  public void delete(UUID id);

}
