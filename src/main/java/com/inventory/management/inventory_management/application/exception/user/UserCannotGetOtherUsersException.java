package com.inventory.management.inventory_management.application.exception.user;

public class UserCannotGetOtherUsersException  extends RuntimeException {
  public UserCannotGetOtherUsersException(String name) {
    super("User " + name + " can not get other users");
  };
}