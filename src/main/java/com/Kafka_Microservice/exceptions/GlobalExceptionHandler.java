package com.Kafka_Microservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleCustomerNotFound(
          CustomerNotFoundException ex) {

    Map<String, Object> response = new HashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("error", "Customer Not Found");
    response.put("message", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(OrderNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleOrderNotFound(
          OrderNotFoundException ex) {

    Map<String, Object> response = new HashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("error", "Order Not Found");
    response.put("message", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateCustomerException.class)
  public ResponseEntity<Map<String, Object>> handleDuplicateCustomer(
          DuplicateCustomerException ex) {

    Map<String, Object> response = new HashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.CONFLICT.value());
    response.put("error", "Duplicate Customer");
    response.put("message", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(InvalidOrderException.class)
  public ResponseEntity<Map<String, Object>> handleInvalidOrder(InvalidOrderException ex) {

    Map<String, Object> response = new HashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Invalid Order");
    response.put("message", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {

    Map<String, Object> response = new HashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.put("error", "Internal Server Error");
    response.put("message", ex.getMessage());

    return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
