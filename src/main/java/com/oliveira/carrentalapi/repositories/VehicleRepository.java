package com.oliveira.carrentalapi.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.carrentalapi.domain.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

  Optional<List<Vehicle>> findByCategoryId(UUID id);

}
