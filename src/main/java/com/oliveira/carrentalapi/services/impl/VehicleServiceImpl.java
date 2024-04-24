package com.oliveira.carrentalapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.oliveira.carrentalapi.domain.dtos.VehicleDto;
import com.oliveira.carrentalapi.domain.exceptions.CategoryNotFoundException;
import com.oliveira.carrentalapi.domain.exceptions.VehicleNotFoundException;
import com.oliveira.carrentalapi.domain.models.Vehicle;
import com.oliveira.carrentalapi.repositories.CategoryRepository;
import com.oliveira.carrentalapi.repositories.VehicleRepository;
import com.oliveira.carrentalapi.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

  private final VehicleRepository vehicleRepository;
  private final CategoryRepository categoryRepository;

  public VehicleServiceImpl(VehicleRepository vehicleRepository, CategoryRepository categoryRepository) {

    this.vehicleRepository = vehicleRepository;

    this.categoryRepository = categoryRepository;

  }

  @Override
  public Vehicle save(VehicleDto vehicleData) {

    this.categoryRepository.findById(vehicleData.category().getId()).orElseThrow(CategoryNotFoundException::new);

    Vehicle newVehicle = new Vehicle(vehicleData);

    vehicleRepository.save(newVehicle);

    return newVehicle;

  }

  @Override
  public Vehicle update(UUID id, VehicleDto vehicleData) {

    this.categoryRepository.findById(vehicleData.category().getId()).orElseThrow(CategoryNotFoundException::new);

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

    if (vehicleData.category() != null)
      vehicle.setCategory(vehicleData.category());

    vehicleRepository.save(vehicle);

    return vehicle;
  }

  @Override
  public List<Vehicle> getAllVehicles() {

    return vehicleRepository.findAll();

  }

  @Override
  public Vehicle findById(UUID id) {

    Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);

    return vehicle;

  }

  @Override
  public void delete(UUID id) {

    Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);

    vehicleRepository.delete(vehicle);

  }

}
