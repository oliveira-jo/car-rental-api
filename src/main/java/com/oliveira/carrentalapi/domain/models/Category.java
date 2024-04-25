package com.oliveira.carrentalapi.domain.models;

import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CATEGORYS")
@Table(name = "CATEGORYS")
@NoArgsConstructor
@Getter
@Setter
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String categoryName;
  private String datails;
  private Integer numBigSuitCases;
  private Integer numSmallSuitCases;
  private Integer numOfPeople;
  private Boolean complete;
  private Float value;
  @OneToMany(mappedBy = "category")
  private List<Vehicle> vehicles;

  public Category(CategoryDto categoryDate) {
    this.categoryName = categoryDate.categoryName();
    this.datails = categoryDate.datails();
    this.numBigSuitCases = categoryDate.numBigSuitCases();
    this.numSmallSuitCases = categoryDate.numSmallSuitCases();
    this.numOfPeople = categoryDate.numOfPeople();
    this.complete = categoryDate.complete();
    this.value = categoryDate.value();
  }

}
