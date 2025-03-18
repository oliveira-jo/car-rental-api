package com.oliveira.carrentalapi.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.oliveira.carrentalapi.domain.dtos.VehicleDto;
import com.oliveira.carrentalapi.domain.dtos.VehicleResponseDto;
import com.oliveira.carrentalapi.domain.exceptions.CategoryNotFoundException;
import com.oliveira.carrentalapi.domain.exceptions.VehicleNotFoundException;
import com.oliveira.carrentalapi.domain.mapper.VehicleMapper;
import com.oliveira.carrentalapi.domain.mapper.VehicleResponseMapper;
import com.oliveira.carrentalapi.domain.models.Vehicle;
import com.oliveira.carrentalapi.repositories.CategoryRepository;
import com.oliveira.carrentalapi.repositories.VehicleRepository;
import com.oliveira.carrentalapi.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

  private final VehicleRepository vehicleRepository;
  private final CategoryRepository categoryRepository;
  private final VehicleMapper vehicleMapper;
  private final VehicleResponseMapper vehicleResponseMapper;

  public VehicleServiceImpl(VehicleRepository vehicleRepository, CategoryRepository categoryRepository,
      VehicleMapper vehicleMapper, VehicleResponseMapper vehicleResponseMapper) {

    this.vehicleRepository = vehicleRepository;
    this.categoryRepository = categoryRepository;
    this.vehicleMapper = vehicleMapper;
    this.vehicleResponseMapper = vehicleResponseMapper;

  }

  @Override
  public VehicleResponseDto save(VehicleDto vehicleData) {

    UUID categoryId = UUID.fromString(vehicleData.categoryId());

    var category = this.categoryRepository.findById(categoryId)
        .orElseThrow(CategoryNotFoundException::new);

    Vehicle vehicle = new Vehicle(vehicleData);

    vehicle.setCategory(category);

    this.vehicleRepository.save(vehicle);

    return vehicleResponseMapper.vehicleResponseToVehicleDto(vehicle);

  }

  @Override
  public VehicleDto update(UUID id, VehicleDto vehicleData) {

    UUID categoryId = UUID.fromString(vehicleData.categoryId());

    var category = this.categoryRepository.findById(categoryId)
        .orElseThrow(CategoryNotFoundException::new);

    var vehicle = vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);

    if (!vehicleData.model().isEmpty())
      vehicle.setModel(vehicleData.model());

    if (!vehicleData.plate().isEmpty())
      vehicle.setPlate(vehicleData.plate());

    if (!vehicleData.color().isEmpty())
      vehicle.setColor(vehicleData.color());

    if (vehicleData.complete() != null)
      vehicle.setComplete(vehicleData.complete());

    if (Optional.ofNullable(vehicleData.mileage()).orElse(0) != 0)
      vehicle.setMileage(vehicleData.mileage());

    if (vehicleData.ative() != null)
      vehicle.setAtive(vehicleData.ative());

    if (!vehicleData.categoryId().isEmpty())
      vehicle.setCategory(category);

    vehicleRepository.save(vehicle);

    return vehicleMapper.vehicleToVehicleDto(vehicle);

  }

  @Override
  public List<VehicleResponseDto> getAllVehicles() {

    List<Vehicle> vehicles = this.vehicleRepository.findAll();
    List<VehicleResponseDto> newVehicles = new ArrayList<VehicleResponseDto>();

    for (Vehicle vehicle : vehicles) {

      newVehicles.add(vehicleResponseMapper.vehicleResponseToVehicleDto(vehicle));

    }

    return newVehicles;

  }

  @Override
  public VehicleDto findById(UUID id) {

    Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);

    return vehicleMapper.vehicleToVehicleDto(vehicle);

  }

  @Override
  public void delete(UUID id) {

    Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);

    vehicleRepository.delete(vehicle);

  }

}
