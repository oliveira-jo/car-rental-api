package com.oliveira.carrentalapi.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.carrentalapi.domain.models.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

  Optional<Category> findByCategoryName(String categoryName);

}
