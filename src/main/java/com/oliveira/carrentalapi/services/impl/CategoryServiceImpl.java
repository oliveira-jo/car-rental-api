package com.oliveira.carrentalapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;
import com.oliveira.carrentalapi.domain.exceptions.CategoryNotFoundException;
import com.oliveira.carrentalapi.domain.models.Category;
import com.oliveira.carrentalapi.repositories.CategoryRepository;
import com.oliveira.carrentalapi.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {

    this.categoryRepository = categoryRepository;

  }

  @Override
  public Category save(CategoryDto categoryData) {

    Category newCategory = new Category(categoryData);

    this.categoryRepository.save(newCategory);

    return newCategory;

  }

  @Override
  public Category update(UUID id, CategoryDto categoryData) {

    // Exists?
    Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

    // Test
    if (!categoryData.categoryName().isEmpty())
      category.setCategoryName(categoryData.categoryName());

    if (!categoryData.datails().isEmpty())
      category.setDatails(categoryData.datails());

    if (Optional.ofNullable(categoryData.numBigSuitCases()).orElse(0) != 0)
      category.setNumBigSuitCases(categoryData.numBigSuitCases());

    if (Optional.ofNullable(categoryData.numSmallSuitCases()).orElse(0) != 0)
      category.setNumSmallSuitCases(categoryData.numSmallSuitCases());

    if (Optional.ofNullable(categoryData.numOfPeople()).orElse(0) != 0)
      category.setNumOfPeople(categoryData.numOfPeople());

    if (categoryData.complete() != null)
      category.setComplete(categoryData.complete());

    if (categoryData.value() != null)
      category.setValue(categoryData.value());

    // Update
    this.categoryRepository.save(category);

    return category;
  }

  @Override
  public void delete(UUID id) {

    Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

    this.categoryRepository.delete(category);

  }

  @Override
  public List<Category> findAllCategories() {

    return this.categoryRepository.findAll();

  }

  @Override
  public Category findByName(String name) {

    var existCategory = this.categoryRepository.findByCategoryName(name).orElseThrow(CategoryNotFoundException::new);

    return existCategory;
  }

}
