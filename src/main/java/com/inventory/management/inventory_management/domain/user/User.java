package com.inventory.management.inventory_management.domain.user;

import com.inventory.management.inventory_management.infrastructure.model.UserModel.UserType;

public class User {

  private String id;
  private String name;

  private UserType userType;

  private String email;

  public User() {
  }

  public User(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  // Getters and Setters

  public UserType getUserType() {
    return this.userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
