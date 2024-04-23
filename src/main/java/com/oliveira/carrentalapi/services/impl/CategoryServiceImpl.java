package com.oliveira.carrentalapi.services.impl;

import java.util.List;
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
  public CategoryDto insert(CategoryDto date) {

    var existCategory = this.categoryRepository.findByCategoryName(date.categoryName());

    if (existCategory == null)
      throw new CategoryNotFoundException(" * Category Already exists! * ");

    Category newCategory = new Category(date.categoryName(), date.datails(), date.numBigSuitCases(),
        date.numSmallSuitCases(),
        date.numOfPeople(), date.complete(), date.value());

    this.categoryRepository.save(newCategory);

    return new CategoryDto(newCategory.getCategoryName(), newCategory.getDatails(),
        newCategory.getNumBigSuitCases(), newCategory.getNumSmallSuitCases(), newCategory.getNumOfPeople(),
        newCategory.isComplete(), newCategory.getValue());

  }

  @Override
  public CategoryDto update(UUID id, CategoryDto date) {

    var existCategory = this.categoryRepository.findById(id);

    if (existCategory == null)
      throw new CategoryNotFoundException(" * Category not found! * ");

    Category newCategory = new Category(date.categoryName(), date.datails(), date.numBigSuitCases(),
        date.numSmallSuitCases(),
        date.numOfPeople(), date.complete(), date.value());

    this.categoryRepository.save(newCategory);

    return new CategoryDto(newCategory.getCategoryName(), newCategory.getDatails(),
        newCategory.getNumBigSuitCases(), newCategory.getNumSmallSuitCases(), newCategory.getNumOfPeople(),
        newCategory.isComplete(), newCategory.getValue());
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
  public CategoryDto findByName(String name) {

    var existCategory = this.categoryRepository.findByCategoryName(name);

    if (existCategory == null)
      throw new CategoryNotFoundException(" * Category Already exists! * ");

    return new CategoryDto(existCategory.getCategoryName(), existCategory.getDatails(),
        existCategory.getNumBigSuitCases(), existCategory.getNumSmallSuitCases(), existCategory.getNumOfPeople(),
        existCategory.isComplete(), existCategory.getValue());
  }

}
