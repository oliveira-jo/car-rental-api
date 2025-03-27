package com.oliveira.carrentalapi.domain.exceptions;

public class InvalidDataException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidDataException(String msg) {
    super(msg);
  }

  public InvalidDataException(String msg, Throwable cause) {
    super(msg, cause);
  }

}
