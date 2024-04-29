package com.oliveira.carrentalapi.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;
import com.oliveira.carrentalapi.domain.exceptions.CategoryNotFoundException;
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

  @Override
  public CategoryDto save(CategoryDto categoryData) {

    Category newCategory = new Category(categoryData);

    this.categoryRepository.save(newCategory);

    return categoryMapper.categoryToCategoryDto(newCategory);

    // new CategoryDto(newCategory.getId(), newCategory.getCategoryName(),
    // newCategory.getDatails(),
    // newCategory.getNumBigSuitCases(), newCategory.getNumSmallSuitCases(),
    // newCategory.getNumOfPeople(),
    // newCategory.getComplete(), newCategory.getValue());

  }

  @Override
  public CategoryDto update(UUID id, CategoryDto categoryData) {

    Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

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

    return categoryMapper.categoryToCategoryDto(category);

    // new CategoryDto(category.getId(), category.getCategoryName(),
    // category.getDatails(),
    // category.getNumBigSuitCases(), category.getNumSmallSuitCases(),
    // category.getNumOfPeople(),
    // category.getComplete(), category.getValue());
  }

  @Override
  public void delete(UUID id) {

    Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

    this.categoryRepository.delete(category);

  }

  @Override
  public List<CategoryDto> findAllCategories() {

    List<Category> categoriesDB = this.categoryRepository.findAll();
    List<CategoryDto> newCategorys = new ArrayList<CategoryDto>();

    for (Category category : categoriesDB) {

      newCategorys.add(categoryMapper.categoryToCategoryDto(category));
      // newCategorys.add(new CategoryDto(category.getId(),
      // category.getCategoryName(), category.getDatails(),
      // category.getNumBigSuitCases(), category.getNumSmallSuitCases(),
      // category.getNumOfPeople(),
      // category.getComplete(), category.getValue()));

    }

    return newCategorys;

  }

  @Override
  public CategoryDto findByName(String name) {

    var existCategory = this.categoryRepository.findByCategoryName(name).orElseThrow(CategoryNotFoundException::new);

    return categoryMapper.categoryToCategoryDto(existCategory);

    // new CategoryDto(existCategory.getId(), existCategory.getCategoryName(),
    // existCategory.getDatails(),
    // existCategory.getNumBigSuitCases(), existCategory.getNumSmallSuitCases(),
    // existCategory.getNumOfPeople(),
    // existCategory.getComplete(), existCategory.getValue());

  }

}
