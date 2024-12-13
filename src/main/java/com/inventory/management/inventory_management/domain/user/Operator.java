package com.inventory.management.inventory_management.domain.user;

import com.inventory.management.inventory_management.infrastructure.model.UserModel.UserType;

public class Operator extends User {

  private Merchant merchant;

  public Operator() {
    super();
  }

  public Operator(String id, String name, String email) {
    super(id, name, email);
    super.setUserType(UserType.OPERATOR);
  }

  public Operator(String id, String name, String email, Merchant merchant) {
    super(id, name, email);
    super.setUserType(UserType.OPERATOR);
    this.merchant = merchant;
  }

  public Merchant getMerchant() {
    return this.merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  @Override
  public void setUserType(UserType userType) {
    super.setUserType(UserType.OPERATOR);
  }

  @Override
  public UserType getUserType() {
    return UserType.OPERATOR;
  }
}
