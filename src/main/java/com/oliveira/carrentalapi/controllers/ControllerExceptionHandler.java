package com.oliveira.carrentalapi.controllers;

import com.oliveira.carrentalapi.domain.exceptions.StandardError;
import com.oliveira.carrentalapi.domain.exceptions.ValidationError;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.oliveira.carrentalapi.domain.exceptions.AuthorizationException;
import com.oliveira.carrentalapi.domain.exceptions.BusinessException;
import com.oliveira.carrentalapi.domain.exceptions.InvalidDataException;
import com.oliveira.carrentalapi.domain.exceptions.InternalServerErrorException;
import com.oliveira.carrentalapi.domain.exceptions.ObjectNotFoundException;

/**
 * 400 Bad request: An error as an invalid parameter
 * 401 Unauthorized: An invalid access token
 * 403 Forbidden: Access denied - No Applicable
 * 404 Not found: The requested was not found
 * 409 Conflict: A conflict occurred - NOt Applicable
 * 500 Internal Server Error: An error occurred on the server
 */
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<StandardError> authorizationException(AuthorizationException e) {
    StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), e.getMessage(),
        System.currentTimeMillis());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<StandardError> businessException(BusinessException e) {
    StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage(), e.getMessage(),
        System.currentTimeMillis());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<StandardError> internalServerErrorException(InternalServerErrorException e) {
    StandardError err = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e.getMessage(),
        System.currentTimeMillis());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
  }

  @ExceptionHandler(InvalidDataException.class)
  public ResponseEntity<StandardError> invalidDataException(InvalidDataException e) {
    StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getMessage(),
        System.currentTimeMillis());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e) {
    StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getMessage(),
        System.currentTimeMillis());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler({ MethodArgumentNotValidException.class, HttpMessageNotReadableException.class })
  public ResponseEntity<ValidationError> handleValidationException(Exception ex) {

    ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),
        ex.getMessage(), System.currentTimeMillis());

    if (ex instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;

      for (FieldError fieldError : manve.getBindingResult().getFieldErrors()) {
        err.addError(fieldError.getField(), "", fieldError.getDefaultMessage());
      }
    }
    if (ex instanceof HttpMessageNotReadableException) {
      Throwable cause = ex.getCause();
      if (cause instanceof InvalidFormatException) {
        InvalidFormatException ife = (InvalidFormatException) cause;
        String fieldPath = ife.getPath().stream().map(ref -> ref.getFieldName())
            .collect(Collectors.joining("."));

        err.addError(fieldPath, ex.getMessage(), ex.getMessage());

      } else {
        err.addError("unknown", ex.getMessage(), ex.getMessage());
      }
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

}
