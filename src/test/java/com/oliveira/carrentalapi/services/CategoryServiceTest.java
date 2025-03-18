package com.oliveira.carrentalapi.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;
import com.oliveira.carrentalapi.domain.mapper.CategoryMapper;
import com.oliveira.carrentalapi.repositories.CategoryRepository;
import com.oliveira.carrentalapi.services.impl.CategoryServiceImpl;

public class CategoryServiceTest {

  @Mock
  private CategoryRepository categoryRepository;

  @Mock
  private CategoryMapper categoryMapper;

  @Autowired
  @InjectMocks
  private CategoryServiceImpl categoryService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should create user when everything is okay")
  void saveCase1() {

    UUID id = new UUID(0, 9);

    CategoryDto data = new CategoryDto(id, "Premium", "Completo", 2, 2, 4, true, 229F);

    categoryService.save(data);

    verify(categoryRepository, times(1)).save(any());

    when(categoryService.save(data)).thenReturn(data);

  }

  @Test
  @DisplayName("Should generate an error when user have a variable ampy")
  void saveCase2() {

    CategoryDto data = new CategoryDto(null, "Premium", "Completo", 2, 2, 4, true, 229F);

    categoryService.save(data);

    verify(categoryRepository, times(1)).save(any());

    when(categoryService.save(data)).thenReturn(data);

  }

}
