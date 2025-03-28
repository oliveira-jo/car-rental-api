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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.oliveira.carrentalapi.domain.dtos.request.CategoryRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.CategoryResponseDto;
import com.oliveira.carrentalapi.domain.dtos.response.CategoryVehicleResponseDto;
import com.oliveira.carrentalapi.services.CategoryService;

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
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryResponseDto> save(@RequestBody @Valid CategoryRequestDto categoryDate) {

    return ResponseEntity.ok().body(
        this.categoryService.save(categoryDate));

  }

  @Operation(summary = "Update a category by id", method = "PUT")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryResponseDto> update(@PathVariable UUID id,
      @RequestBody @Valid CategoryRequestDto categoryDate) {

    return ResponseEntity.ok().body(
        this.categoryService.update(id, categoryDate));

  }

  @Operation(summary = "Delete a category by id", method = "DELETE")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {

    this.categoryService.delete(id);

    return ResponseEntity.ok().build();

  }

  @Operation(summary = "Get a Cagegorie by a provide id", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @GetMapping(value = "/{id}")
  public ResponseEntity<CategoryResponseDto> findById(@PathVariable UUID id) {

    return ResponseEntity.ok().body(
        this.categoryService.findById(id));

  }

  @Operation(summary = "Get the vehicles of a Cagegorie by a provide id", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @GetMapping(value = "/{id}/vehicles")
  public ResponseEntity<CategoryVehicleResponseDto> findVehiclesByCategoryId(@PathVariable UUID id) {

    return ResponseEntity.ok().body(
        this.categoryService.findVehiclesByCategoryId(id));

  }

  @Operation(summary = "Get All Cagegories", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @GetMapping()
  public ResponseEntity<List<CategoryResponseDto>> getAll() {

    return ResponseEntity.ok().body(
        this.categoryService.getAll());

  }

}
