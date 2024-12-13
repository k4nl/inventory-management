package com.inventory.management.inventory_management.domain.user;

import com.inventory.management.inventory_management.infrastructure.model.UserModel.UserType;
import java.util.Set;

public class Merchant extends  User {

  private Set<Operator> operators;

  public Merchant() {
    super();
    super.setUserType(UserType.MERCHANT);
  }

  public Merchant(String id, String name, String email) {
    super(id, name, email);
    super.setUserType(UserType.MERCHANT);
  }

  public Merchant(String id, String name, String email, Set<Operator> operators) {
    super(id, name, email);
    super.setUserType(UserType.MERCHANT);
    this.operators = operators;
  }

  public Set<Operator> getOperators() {
    return this.operators;
  }

  public void setOperators(Set<Operator> operators) {
    this.operators = operators;
  }

  @Override
  public void setUserType(UserType userType) {
    super.setUserType(UserType.MERCHANT);
  }

  @Override
  public UserType getUserType() {
    return UserType.MERCHANT;
  }

}
