package com.oliveira.carrentalapi.domain.models;

import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.VehicleDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "VEHICLES")
@Table(name = "VEHICLES")
@NoArgsConstructor
@Getter
@Setter
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

  public Vehicle(VehicleDto vehicleData) {
    this.model = vehicleData.model();
    this.plate = vehicleData.plate();
    this.color = vehicleData.color();
    this.complete = vehicleData.complete();
    this.mileage = vehicleData.mileage();
    this.ative = vehicleData.ative();
  }
}
