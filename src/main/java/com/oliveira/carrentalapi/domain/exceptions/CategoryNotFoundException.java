package com.oliveira.carrentalapi.domain.exceptions;

public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException() {
  }

  public CategoryNotFoundException(String messagem) {
    super(messagem);
  }

}
