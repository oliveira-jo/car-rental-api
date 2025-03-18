package com.oliveira.carrentalapi.controllers;

import java.util.UUID;
import java.util.List;

import org.springframework.http.MediaType;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/category", produces = { "application/json" })
@Tag(name = "CategoryController")
public class CategoryController {

  private CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {

    this.categoryService = categoryService;

  }

  @Operation(summary = "Save an user with all date is okay", method = "POST")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "422", description = "Invalid Dates!"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryDto> save(@RequestBody @Valid CategoryDto categoryDate) {

    var newCategory = this.categoryService.save(categoryDate);

    return ResponseEntity.ok().body(newCategory);

  }

  @Operation(summary = "Update a category by id", method = "PUT")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "422", description = "Invalid Dates!"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryDto> update(@PathVariable UUID id, @RequestBody @Valid CategoryDto categoryDate) {

    var updateCategory = this.categoryService.update(id, categoryDate);

    return ResponseEntity.ok().body(updateCategory);

  }

  @Operation(summary = "Delete a category by id", method = "DELETE")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "422", description = "Invalid Dates!"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<CategoryDto> delete(@PathVariable UUID id) {

    this.categoryService.delete(id);

    return ResponseEntity.ok().build();

  }

  @Operation(summary = "Get All Cagegories", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "422", description = "Invalid Dates!"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @GetMapping()
  public ResponseEntity<List<CategoryDto>> getCategory() {

    var categories = this.categoryService.findAllCategories();

    return ResponseEntity.ok().body(categories);

  }

}
