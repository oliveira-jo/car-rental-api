package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.request.VehicleRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.VehicleResponseDto;
import com.oliveira.carrentalapi.domain.dtos.response.VehicleWithoutCategoryResponseDto;

public interface VehicleService {

  public VehicleResponseDto save(VehicleRequestDto vehicleData);

  public VehicleResponseDto update(UUID id, VehicleRequestDto vehicleData);

  public List<VehicleWithoutCategoryResponseDto> findAll();

  public VehicleResponseDto findById(UUID id);

  public void delete(UUID id);

}
