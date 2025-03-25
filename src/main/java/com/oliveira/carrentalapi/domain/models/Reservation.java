package com.oliveira.carrentalapi.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "RESERVATION")
@Table(name = "RESERVATION")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private LocalDateTime pickUpDate;

  private LocalDateTime returnDate;

  private Float dailyRentalValue;

  private Long qtdDays;

  private BigDecimal totalValue;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  private LocalDateTime createdAt;

  public Reservation() {
  }

  public Reservation(LocalDateTime pickUpDate, LocalDateTime returnDate, Category category, BigDecimal totalValue,
      User user, LocalDateTime createdAt) {
    this.pickUpDate = pickUpDate;
    this.returnDate = returnDate;
    this.category = category;
    this.totalValue = totalValue;
    this.user = user;
    this.createdAt = createdAt;
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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
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

  @Override
  public String toString() {
    return "Reservation [id=" + id + ", pickUpDate=" + pickUpDate + ", returnDate=" + returnDate + ", dailyRentalValue="
        + dailyRentalValue + ", qtdDays=" + qtdDays + ", totalValue=" + totalValue + ", user=" + user.getUsername()
        + ", category="
        + category.getCategoryName() + "]";
  }

}
