package com.inventory.management.inventory_management.application.exception.user;

import java.util.Optional;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String userName) {
    super("User with name " + userName + " not found");
  };
}
