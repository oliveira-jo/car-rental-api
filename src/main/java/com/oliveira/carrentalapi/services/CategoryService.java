package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;
import com.oliveira.carrentalapi.domain.models.Category;

public interface CategoryService {
  public CategoryDto insert(CategoryDto categoryDate);

  public CategoryDto update(UUID id, CategoryDto categoryDate);

  public void delete(UUID id);

  public List<Category> findAllCategories();

  public CategoryDto findByName(String name);
}
