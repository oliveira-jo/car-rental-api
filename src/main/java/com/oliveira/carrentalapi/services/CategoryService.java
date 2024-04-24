package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;
import com.oliveira.carrentalapi.domain.models.Category;

public interface CategoryService {
  public Category save(CategoryDto categoryDate);

  public Category update(UUID id, CategoryDto categoryDate);

  public void delete(UUID id);

  public List<Category> findAllCategories();

  public Category findByName(String name);
}
