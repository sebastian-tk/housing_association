package com.app.housing_association.common.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static java.util.stream.Collectors.joining;

@ControllerAdvice()
public class ControllerExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorMessage> sqlException(IllegalArgumentException ex) {
    return new ResponseEntity<>(
            new ErrorMessage(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST.value()),
            HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorMessage> sqlException(DataIntegrityViolationException ex) {
    return new ResponseEntity<>(
        new ErrorMessage(
            ex
              .getCause()
              .getCause()
              .getLocalizedMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorMessage> globalException(RuntimeException ex) {

    return new ResponseEntity<>(
        new ErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorMessage> constraintViolationException(
      ConstraintViolationException ex) {
    return new ResponseEntity<>(
        new ErrorMessage(
            ex
                .getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessageTemplate)
                .collect(joining(" ")),
            HttpStatus.INTERNAL_SERVER_ERROR.value()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
