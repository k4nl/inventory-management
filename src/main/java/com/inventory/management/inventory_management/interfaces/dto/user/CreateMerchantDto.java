package com.inventory.management.inventory_management.interfaces.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateMerchantDto(
    @NotNull(message = "Name is required") String name,
    @NotNull(message = "Email is required") @Email(message = "Email is invalid") String email
) {

  public CreateMerchantDto(String name, String email) {
    this.name = name;
    this.email = email;
  }

  @Override
  public String name() {
    return this.name.trim().toLowerCase();
  }

  @Override
  public String email() {
    return this.email.trim().toLowerCase();
  }
}
