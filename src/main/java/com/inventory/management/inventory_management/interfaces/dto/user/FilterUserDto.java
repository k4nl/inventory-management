package com.inventory.management.inventory_management.interfaces.dto.user;

import com.inventory.management.inventory_management.infrastructure.model.UserModel.UserType;
import java.util.List;

public class FilterUserDto {

  private String name;
  private String email;

  private List<UserType> userType;

  private List<String> operatorIds;

  private List<String> merchantIds;

  public FilterUserDto() {
  }

  public FilterUserDto(String name, String email, List<UserType> userType, List<String> operatorIds, List<String> merchantIds) {
    this.name = name;
    this.email = email;
    this.userType = userType;
    this.operatorIds = operatorIds;
    this.merchantIds = merchantIds;
  }


  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setUserType(List<UserType> userType) {
    this.userType = userType;
  }

  public List<UserType> getUserType() {
    return userType;
  }

  public void setOperatorIds(List<String> operatorIds) {
    this.operatorIds = operatorIds;
  }

  public List<String> getOperatorIds() {
    return operatorIds;
  }

  public void setMerchantIds(List<String> merchantIds) {
    this.merchantIds = merchantIds;
  }

  public List<String> getMerchantIds() {
    return merchantIds;
  }

}
