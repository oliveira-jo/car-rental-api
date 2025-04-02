package com.oliveira.carrentalapi.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.oliveira.carrentalapi.domain.dtos.request.CategoryRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.CategoryResponseDto;
import com.oliveira.carrentalapi.domain.dtos.response.CategoryVehicleResponseDto;
import com.oliveira.carrentalapi.domain.exceptions.ObjectNotFoundException;
import com.oliveira.carrentalapi.domain.mapper.CategoryMapper;
import com.oliveira.carrentalapi.domain.models.Category;
import com.oliveira.carrentalapi.repositories.CategoryRepository;
import com.oliveira.carrentalapi.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
    this.categoryRepository = categoryRepository;
    this.categoryMapper = categoryMapper;

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public CategoryResponseDto save(CategoryRequestDto categoryData) {

    Category newCategory = new Category(categoryData);

    this.categoryRepository.save(new Category(categoryData));

    return categoryMapper.toCategoryResponseDto(newCategory);

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public CategoryResponseDto update(UUID id, CategoryRequestDto categoryData) {

    Category category = this.categoryRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with provide id"));

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

    this.categoryRepository.save(category);

    return categoryMapper.toCategoryResponseDto(category);

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public void delete(UUID id) {

    Category category = this.categoryRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with provide id"));

    this.categoryRepository.delete(category);

  }

  @Override
  public List<CategoryResponseDto> getAll() {

    List<Category> categoriesDB = this.categoryRepository.findAll();
    List<CategoryResponseDto> newCategorys = new ArrayList<CategoryResponseDto>();

    for (Category category : categoriesDB)
      newCategorys.add(categoryMapper.toCategoryResponseDto(category));

    return newCategorys;

  }

  @Override
  public CategoryResponseDto findById(UUID id) {

    var existCategory = this.categoryRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with provide id"));

    return categoryMapper.toCategoryResponseDto(existCategory);

  }

  @Override
  public CategoryVehicleResponseDto findVehiclesByCategoryId(UUID id) {

    var existCategory = this.categoryRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with provide id"));

    return categoryMapper.toCategoryVehicleResponseDto(existCategory);

  }

  @Override
  public CategoryResponseDto findByName(String name) {

    var existCategory = this.categoryRepository.findByCategoryName(name)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with provide id"));

    return categoryMapper.toCategoryResponseDto(existCategory);

  }

}
