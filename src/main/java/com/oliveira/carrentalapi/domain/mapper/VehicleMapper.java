package com.oliveira.carrentalapi.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.oliveira.carrentalapi.domain.dtos.VehicleDto;
import com.oliveira.carrentalapi.domain.models.Vehicle;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {

  @Mapping(source = "category.id", target = "categoryId")
  VehicleDto vehicleToVehicleDto(Vehicle vehicle);

}
