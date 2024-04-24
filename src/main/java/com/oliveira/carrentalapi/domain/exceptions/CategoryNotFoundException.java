package com.oliveira.carrentalapi.domain.exceptions;

public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException() {
    super();
  }

  public CategoryNotFoundException(String messagem) {
    super(messagem);
  }

}
