package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;

public interface CategoryService {

  public CategoryDto save(CategoryDto categoryDate);

  public CategoryDto update(UUID id, CategoryDto categoryDate);

  public void delete(UUID id);

  public List<CategoryDto> findAllCategories();

  public CategoryDto findByName(String name);

}
