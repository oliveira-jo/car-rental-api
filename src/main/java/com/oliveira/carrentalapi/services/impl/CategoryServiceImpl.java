package com.oliveira.carrentalapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.oliveira.carrentalapi.domain.dtos.request.CategoryRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.CategoryResponseDto;
import com.oliveira.carrentalapi.domain.dtos.response.CategoryVehicleResponseDto;
import com.oliveira.carrentalapi.domain.exceptions.BusinessException;
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

    Optional<Category> fromDB = this.categoryRepository.findByCategoryName(categoryData.categoryName());
    if (fromDB.isPresent())
      throw new BusinessException(
          "Category alreay exists in db with provide name:" + categoryData.categoryName());

    return categoryMapper.toCategoryResponseDto(
        this.categoryRepository.save(new Category(categoryData)));

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public CategoryResponseDto update(UUID id, CategoryRequestDto categoryData) {

    Category category = this.categoryRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with provide id"));

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

    return categoryMapper.toCategoryResponseDto(
        this.categoryRepository.save(category));

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

    return this.categoryRepository.findAll().stream()
        .map(categoryMapper::toCategoryResponseDto).toList();

  }

  @Override
  public CategoryResponseDto findById(UUID id) {

    return this.categoryRepository.findById(id).map(categoryMapper::toCategoryResponseDto)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with provide id"));

  }

  @Override
  public CategoryVehicleResponseDto findVehiclesByCategoryId(UUID id) {

    return this.categoryRepository.findById(id).map(categoryMapper::toCategoryVehicleResponseDto)
        .orElseThrow(() -> new ObjectNotFoundException("Category not found with provide id"));

  }

  @Override
  public CategoryResponseDto findByName(String name) {

    return this.categoryRepository.findByCategoryName(name).map(categoryMapper::toCategoryResponseDto).orElseThrow(
        () -> new ObjectNotFoundException("Category not found with provide name"));

  }

}
