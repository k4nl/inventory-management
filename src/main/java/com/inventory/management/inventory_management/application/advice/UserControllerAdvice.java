package com.inventory.management.inventory_management.application.advice;

import com.inventory.management.inventory_management.application.exception.user.UserAlreadyExistsException;
import com.inventory.management.inventory_management.application.exception.user.UserCannotGetOtherUsersException;
import com.inventory.management.inventory_management.application.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
    // Retorne uma resposta adequada com status 400 (Bad Request)
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
    // Retorne uma resposta adequada com status 404 (Not Found)
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserCannotGetOtherUsersException.class)
  public ResponseEntity<String> handleUserCannotGetOtherUsersException(UserCannotGetOtherUsersException ex) {
    // Retorne uma resposta adequada com status 403 (Forbidden)
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
  }
}
