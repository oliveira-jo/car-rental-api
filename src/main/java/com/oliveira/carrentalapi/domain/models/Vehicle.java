package com.oliveira.carrentalapi.domain.models;

import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.VehicleDto;

import jakarta.persistence.Entity;
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
  private UUID id;
  private String model;
  private String plate;
  private String color;
  private Boolean complete;
  private Integer mileage;
  private Boolean ative;

  public Vehicle(VehicleDto vehicleData) {
    this.model = vehicleData.model();
    this.plate = vehicleData.plate();
    this.color = vehicleData.color();
    this.complete = vehicleData.complete();
    this.mileage = vehicleData.mileage();
    this.ative = vehicleData.ative();

  }
}
