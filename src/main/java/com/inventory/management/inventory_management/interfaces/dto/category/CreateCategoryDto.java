package com.inventory.management.inventory_management.interfaces.dto.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCategoryDto(@NotNull String name) {

  public CreateCategoryDto(String name) {
    this.name = name;
  }

  @Override
  public String name() {
    return this.name.trim().toLowerCase();
  }
}
