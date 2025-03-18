package com.oliveira.carrentalapi.domain.models;

import java.util.List;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.oliveira.carrentalapi.domain.dtos.CategoryDto;

@Entity(name = "CATEGORYS")
@Table(name = "CATEGORYS")
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

  public Category() {
  }

  public Category(CategoryDto categoryDate) {
    this.categoryName = categoryDate.categoryName();
    this.datails = categoryDate.datails();
    this.numBigSuitCases = categoryDate.numBigSuitCases();
    this.numSmallSuitCases = categoryDate.numSmallSuitCases();
    this.numOfPeople = categoryDate.numOfPeople();
    this.complete = categoryDate.complete();
    this.value = categoryDate.value();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getDatails() {
    return datails;
  }

  public void setDatails(String datails) {
    this.datails = datails;
  }

  public Integer getNumBigSuitCases() {
    return numBigSuitCases;
  }

  public void setNumBigSuitCases(Integer numBigSuitCases) {
    this.numBigSuitCases = numBigSuitCases;
  }

  public Integer getNumSmallSuitCases() {
    return numSmallSuitCases;
  }

  public void setNumSmallSuitCases(Integer numSmallSuitCases) {
    this.numSmallSuitCases = numSmallSuitCases;
  }

  public Integer getNumOfPeople() {
    return numOfPeople;
  }

  public void setNumOfPeople(Integer numOfPeople) {
    this.numOfPeople = numOfPeople;
  }

  public Boolean getComplete() {
    return complete;
  }

  public void setComplete(Boolean complete) {
    this.complete = complete;
  }

  public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

}
