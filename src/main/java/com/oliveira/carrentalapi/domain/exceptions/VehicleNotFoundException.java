package com.oliveira.carrentalapi.domain.exceptions;

public class VehicleNotFoundException extends RuntimeException {

  public VehicleNotFoundException() {
    super();
  }

  public VehicleNotFoundException(String message) {
    super(message);
  }

}
