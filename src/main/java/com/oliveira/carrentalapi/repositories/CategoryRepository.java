package com.oliveira.carrentalapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.carrentalapi.domain.models.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

  Category findByCategoryName(String categoryName);

}
