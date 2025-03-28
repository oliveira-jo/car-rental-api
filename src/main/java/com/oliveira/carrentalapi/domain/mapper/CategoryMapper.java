package com.oliveira.carrentalapi.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.oliveira.carrentalapi.domain.dtos.CategoryResponseDto;
import com.oliveira.carrentalapi.domain.dtos.CategoryVehicleResponseDto;
import com.oliveira.carrentalapi.domain.models.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

  CategoryResponseDto toCategoryResponseDto(Category category);

  @Mapping(target = "vehicles.category", ignore = true)
  CategoryVehicleResponseDto toCategoryVehicleResponseDto(Category category);

}
