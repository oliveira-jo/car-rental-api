package com.oliveira.carrentalapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.oliveira.carrentalapi.domain.dtos.request.VehicleRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.VehicleResponseDto;
import com.oliveira.carrentalapi.domain.exceptions.ObjectNotFoundException;
import com.oliveira.carrentalapi.domain.mapper.VehicleMapper;
import com.oliveira.carrentalapi.domain.models.Vehicle;
import com.oliveira.carrentalapi.repositories.CategoryRepository;
import com.oliveira.carrentalapi.repositories.VehicleRepository;
import com.oliveira.carrentalapi.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

  private final VehicleRepository vehicleRepository;
  private final CategoryRepository categoryRepository;
  private final VehicleMapper vehicleMapper;

  public VehicleServiceImpl(VehicleRepository vehicleRepository, CategoryRepository categoryRepository,
      VehicleMapper vehicleMapper) {
    this.vehicleRepository = vehicleRepository;
    this.categoryRepository = categoryRepository;
    this.vehicleMapper = vehicleMapper;

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public VehicleResponseDto save(VehicleRequestDto vehicleData) {

    UUID categoryId = UUID.fromString(vehicleData.categoryId());

    var category = this.categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with id: " + categoryId));

    Vehicle vehicle = new Vehicle(vehicleData);
    vehicle.setCategory(category);

    return vehicleMapper.toVehicleResponseDto(
        this.vehicleRepository.save(vehicle));

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public VehicleResponseDto update(UUID id, VehicleRequestDto vehicleData) {

    UUID categoryId = UUID.fromString(vehicleData.categoryId());

    var category = this.categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with id: " + categoryId));

    var vehicle = vehicleRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Vehicle not found with provided id: " + id));

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

    return vehicleMapper.toVehicleResponseDto(
        vehicleRepository.save(vehicle));

  }

  @Override
  public List<VehicleResponseDto> getAll() {

    return this.vehicleRepository.findAll().stream()
        .map(vehicleMapper::toVehicleResponseDto).toList();

  }

  @Override
  public VehicleResponseDto findById(UUID id) {

    return this.vehicleRepository.findById(id).map(vehicleMapper::toVehicleResponseDto).orElseThrow(
        () -> new ObjectNotFoundException("Vehicle not found with provide id"));

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public void delete(UUID id) {

    vehicleRepository.delete(
        vehicleRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Vehicle not found")));

  }

}
