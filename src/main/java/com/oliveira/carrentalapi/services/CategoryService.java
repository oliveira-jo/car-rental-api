package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.request.CategoryRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.CategoryResponseDto;
import com.oliveira.carrentalapi.domain.dtos.response.CategoryVehicleResponseDto;

public interface CategoryService {

  public CategoryResponseDto save(CategoryRequestDto categoryDate);

  public CategoryResponseDto update(UUID id, CategoryRequestDto categoryDate);

  public void delete(UUID id);

  public List<CategoryResponseDto> getAll();

  public CategoryResponseDto findById(UUID id);

  public CategoryVehicleResponseDto findVehiclesByCategoryId(UUID id);

  public CategoryResponseDto findByName(String name);

}
