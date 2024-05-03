package com.oliveira.carrentalapi.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.oliveira.carrentalapi.domain.dtos.VehicleResponseDto;
import com.oliveira.carrentalapi.domain.models.Vehicle;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleResponseMapper {

  @Mapping(source = "category.categoryName", target = "categoryName")
  VehicleResponseDto vehicleResponseToVehicleDto(Vehicle vehicle);

}
