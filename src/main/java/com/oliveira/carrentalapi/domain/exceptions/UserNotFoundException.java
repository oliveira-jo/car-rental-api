package com.oliveira.carrentalapi.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String string) {
    super(string);
  }

}
