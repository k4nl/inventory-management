package com.inventory.management.inventory_management.domain.user;

import com.inventory.management.inventory_management.model.User.UserType;

public class Admin extends User {

  public Admin() {
    super();
  }

  public Admin(String id, String name) {
    super(id, name);
    super.setUserType(UserType.ADMIN);
  }

  @Override
  public void setUserType(UserType userType) {
    super.setUserType(UserType.ADMIN);
  }

  @Override
  public UserType getUserType() {
    return UserType.ADMIN;
  }


}
