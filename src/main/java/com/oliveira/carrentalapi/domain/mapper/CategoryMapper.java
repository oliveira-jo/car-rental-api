package com.oliveira.carrentalapi.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.oliveira.carrentalapi.domain.dtos.response.CategoryResponseDto;
import com.oliveira.carrentalapi.domain.dtos.response.CategoryVehicleResponseDto;
import com.oliveira.carrentalapi.domain.models.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

  CategoryResponseDto toCategoryResponseDto(Category category);

  CategoryVehicleResponseDto toCategoryVehicleResponseDto(Category category);

}
