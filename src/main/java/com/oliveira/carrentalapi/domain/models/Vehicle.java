package com.oliveira.carrentalapi.domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.dtos.request.VehicleRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String model;
  private String imgUrl;
  private String plate;
  private String color;
  private Boolean complete;
  private Integer mileage;
  private Boolean ative;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @OneToMany(mappedBy = "vehicle")
  private List<Reservation> reservations = new ArrayList<>();

  public Vehicle() {
  }

  public Vehicle(UUID id, String model, String imgUrl, String plate, String color, Boolean complete, Integer mileage,
      Boolean ative, Category category) {
    this.id = id;
    this.model = model;
    this.imgUrl = imgUrl;
    this.plate = plate;
    this.color = color;
    this.complete = complete;
    this.mileage = mileage;
    this.ative = ative;
    this.category = category;
  }

  public Vehicle(VehicleRequestDto dto) {
    this.model = dto.model();
    this.imgUrl = dto.imgUrl();
    this.plate = dto.plate();
    this.color = dto.plate();
    this.complete = dto.complete();
    this.mileage = dto.mileage();
    this.ative = dto.ative();
    this.category = new Category(dto.categoryId(), null, null, null, null, null, null);
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

  public String getImgRrl() {
    return imgUrl;
  }

  public void setImgRrl(String img) {
    this.imgUrl = img;
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

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
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
    Vehicle other = (Vehicle) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
