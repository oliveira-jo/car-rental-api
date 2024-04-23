package com.oliveira.carrentalapi.domain.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  private int numBigSuitCases;
  private int numSmallSuitCases;
  private int numOfPeople;
  private boolean complete;
  private Float value;

  public Category(String categoryName, String datails, int numBigSuitCases, int numSmallSuitCases, int numOfPeople,
      boolean complete, Float value) {
    this.categoryName = categoryName;
    this.datails = datails;
    this.numBigSuitCases = numBigSuitCases;
    this.numSmallSuitCases = numSmallSuitCases;
    this.numOfPeople = numOfPeople;
    this.complete = complete;
    this.value = value;

  }

}
