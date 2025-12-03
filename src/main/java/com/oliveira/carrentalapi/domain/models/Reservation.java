package com.oliveira.carrentalapi.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.oliveira.carrentalapi.domain.enums.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reservation")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private LocalDateTime pickUpDate;

  private LocalDateTime returnDate;

  private Float dailyRentalValue;

  private Long qtdDays;

  private BigDecimal totalValue;

  @Enumerated(EnumType.STRING)
  private ReservationStatus status;

  private LocalDateTime createdAt;

  private LocalDateTime updateAt;

  private UUID createBy;

  private UUID updateBy;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "vehicle_id")
  private Vehicle vehicle;

  public Reservation() {
  }

  public Reservation(UUID id, LocalDateTime pickUpDate, LocalDateTime returnDate, Float dailyRentalValue, Long qtdDays,
      BigDecimal totalValue, ReservationStatus status, LocalDateTime createdAt, LocalDateTime updateAt, UUID createBy,
      UUID updateBy, User user, Category category, Vehicle vehicle) {
    this.id = id;
    this.pickUpDate = pickUpDate;
    this.returnDate = returnDate;
    this.dailyRentalValue = dailyRentalValue;
    this.qtdDays = qtdDays;
    this.totalValue = totalValue;
    this.status = status;
    this.createdAt = createdAt;
    this.updateAt = updateAt;
    this.createBy = createBy;
    this.updateBy = updateBy;
    this.user = user;
    this.category = category;
    this.vehicle = vehicle;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Long getQtdDays() {
    return qtdDays;
  }

  public void setQtdDays(Long qtdDays) {
    this.qtdDays = qtdDays;
  }

  public LocalDateTime getPickUpDate() {
    return pickUpDate;
  }

  public void setPickUpDate(LocalDateTime pickUpDate) {
    this.pickUpDate = pickUpDate;
  }

  public LocalDateTime getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(LocalDateTime returnDate) {
    this.returnDate = returnDate;
  }

  public BigDecimal getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(BigDecimal totalValue) {
    this.totalValue = totalValue;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Float getDailyRentalValue() {
    return dailyRentalValue;
  }

  public void setDailyRentalValue(Float dailyRentalValue) {
    this.dailyRentalValue = dailyRentalValue;
  }

  public ReservationStatus getStatus() {
    return status;
  }

  public void setStatus(ReservationStatus status) {
    this.status = status;
  }

  public LocalDateTime getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(LocalDateTime updateAt) {
    this.updateAt = updateAt;
  }

  public UUID getCreateBy() {
    return createBy;
  }

  public void setCreateBy(UUID createBy) {
    this.createBy = createBy;
  }

  public UUID getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(UUID updateBy) {
    this.updateBy = updateBy;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
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
    Reservation other = (Reservation) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
