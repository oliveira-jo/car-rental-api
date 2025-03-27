package com.oliveira.carrentalapi.domain.enums;

public enum ReservationStatus {

  CONFIRMED("confirmed"),
  CANCELED("canceled"),
  FINISHED("finished");

  private String status;

  ReservationStatus(String status) {
    this.status = status;
  }

  public String getRole() {
    return status;
  }

}
