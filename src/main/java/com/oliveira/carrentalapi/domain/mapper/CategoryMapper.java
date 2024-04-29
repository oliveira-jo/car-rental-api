package com.oliveira.carrentalapi.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;
import com.oliveira.carrentalapi.domain.models.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

  CategoryDto categoryToCategoryDto(Category category);

}
