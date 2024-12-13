package com.inventory.management.inventory_management.interfaces.dto.user;

import com.inventory.management.inventory_management.infrastructure.model.UserModel.UserType;

public class CreateUserResponseDto {

  public String message;
  public String status;

  public CreateUserResponseDto(String name, String email, UserType userType) {
    this.message = "User " + name + " created with email " + email + " and type " + userType + ".";
    this.status = "success";
  }


}
