package com.oliveira.carrentalapi.domain.exceptions;

public class BusinessException extends RuntimeException {

  public BusinessException() {
    super();
  }

  public BusinessException(String string) {
    super(string);
  }

}
