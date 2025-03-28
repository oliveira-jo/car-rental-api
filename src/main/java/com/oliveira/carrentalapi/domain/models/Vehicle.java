package com.oliveira.carrentalapi.domain.models;

import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.request.VehicleRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "VEHICLES")
@Table(name = "VEHICLES")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String model;
  private String plate;
  private String color;
  private Boolean complete;
  private Integer mileage;
  private Boolean ative;
  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  public Vehicle() {
  }

  public Vehicle(VehicleRequestDto vehicleData) {
    this.model = vehicleData.model();
    this.plate = vehicleData.plate();
    this.color = vehicleData.color();
    this.complete = vehicleData.complete();
    this.mileage = vehicleData.mileage();
    this.ative = vehicleData.ative();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Boolean getComplete() {
    return complete;
  }

  public void setComplete(Boolean complete) {
    this.complete = complete;
  }

  public Integer getMileage() {
    return mileage;
  }

  public void setMileage(Integer mileage) {
    this.mileage = mileage;
  }

  public Boolean getAtive() {
    return ative;
  }

  public void setAtive(Boolean ative) {
    this.ative = ative;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

}
