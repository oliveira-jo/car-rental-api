package com.oliveira.carrentalapi.domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.request.CategoryRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  // @Column(columnDefinition = "VARCHAR(36)")
  private UUID id;
  private String categoryName;

  @Column(columnDefinition = "TEXT")
  private String details;
  private Integer numSuitcase;
  private Integer numOfPeople;
  private Boolean complete;

  @Column(name = "categoryValue")
  private Float value;

  @OneToMany(mappedBy = "category")
  private List<Vehicle> vehicles = new ArrayList<>();

  @OneToMany(mappedBy = "category")
  private List<Reservation> reservations = new ArrayList<>();

  public Category() {
  }

  public Category(UUID id, String categoryName, String details, Integer numSuitcase, Integer numOfPeople,
      Boolean complete, Float value) {
    this.id = id;
    this.categoryName = categoryName;
    this.details = details;
    this.numSuitcase = numSuitcase;
    this.numOfPeople = numOfPeople;
    this.complete = complete;
    this.value = value;
  }

  public Category(CategoryRequestDto categoryDate) {
    this.categoryName = categoryDate.categoryName();
    this.details = categoryDate.details();
    this.numSuitcase = categoryDate.numSuitcase();
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

  public String getDetails() {
    return details;
  }

  public void setDetails(String datails) {
    this.details = datails;
  }

  public Integer getNumSuitcase() {
    return numSuitcase;
  }

  public void setNumSuitcase(Integer numBigSuitCases) {
    this.numSuitcase = numBigSuitCases;
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

  public List<Reservation> getReservation() {
    return reservations;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Category other = (Category) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
