package com.oliveira.carrentalapi.controllers;

import java.util.UUID;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;
import com.oliveira.carrentalapi.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

  private CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {

    this.categoryService = categoryService;

  }

  @PostMapping
  public ResponseEntity<CategoryDto> save(@RequestBody @Valid CategoryDto categoryDate) {

    var newCategory = this.categoryService.save(categoryDate);

    return ResponseEntity.ok().body(newCategory);

  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDto> update(@PathVariable("id") UUID id, @RequestBody @Valid CategoryDto categoryDate) {

    var updateCategory = this.categoryService.update(id, categoryDate);

    return ResponseEntity.ok().body(updateCategory);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CategoryDto> delete(@PathVariable("id") UUID id) {

    this.categoryService.delete(id);

    return ResponseEntity.ok().build();

  }

  @GetMapping
  public ResponseEntity<List<CategoryDto>> getCategory() {

    var categories = this.categoryService.findAllCategories();

    return ResponseEntity.ok().body(categories);

  }

}
