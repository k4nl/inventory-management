package com.inventory.management.inventory_management.interfaces.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateOperatorDto(
    @NotNull(message = "Name is required") String name,
    @NotNull(message = "Merchant id is required") String merchantId,
    @NotNull(message = "Email is required") @Email(message = "Email is invalid") String email
) {

  public CreateOperatorDto(
      String name,
      String merchantId,
      String email
  ) {
    this.name = name;
    this.merchantId = merchantId;
    this.email = email;
  }

  public String name() {
    return this.name.trim().toLowerCase();
  }

  public String merchantId() {
    return this.merchantId;
  }

  public String email() {
    return this.email.trim().toLowerCase();
  }


}
