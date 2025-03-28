package com.oliveira.carrentalapi.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.oliveira.carrentalapi.domain.dtos.response.VehicleResponseDto;
import com.oliveira.carrentalapi.domain.models.Vehicle;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {

  VehicleResponseDto toVehicleResponseDto(Vehicle vehicle);

}
