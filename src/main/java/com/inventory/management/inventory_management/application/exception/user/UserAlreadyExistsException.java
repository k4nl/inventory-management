package com.inventory.management.inventory_management.application.exception.user;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String name) {
    super("User with name " + name + " already exists");
  };
}
