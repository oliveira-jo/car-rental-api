package com.oliveira.carrentalapi.domain.exceptions;

public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BusinessException(String error) {
    super(error);
  }

}
