package com.inventory.management.inventory_management.interfaces.dto.product;

import com.inventory.management.inventory_management.infrastructure.model.ProductModel;
import com.inventory.management.inventory_management.infrastructure.model.ProductModel.UnitOfMeasure;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CreateProductDto(
    @NotNull @NotEmpty String name,
    String description,
    @Enumerated(EnumType.STRING) UnitOfMeasure unitOfMeasure,
    @NotNull boolean unlimited,
    @NotNull @Enumerated(EnumType.STRING) ProductModel.ReservationType reservationType,
    @NotBlank List<String> categoriesId,

    Float minimumStock,
    @NotNull boolean alertOnLowStock
) {

  public String name() {
    return name.trim().toLowerCase();
  }

  public String description() {
    if (description == null || description.isBlank()) {
      return null;
    }
    return description.trim();
  }

  public List<String> categoriesId() {
    if (categoriesId == null || categoriesId.isEmpty()) {
      return null;
    }
    return categoriesId;
  }

  public boolean unlimited() {
    return unlimited;
  }

  public UnitOfMeasure unitOfMeasure() {
    return unitOfMeasure;
  };

  public Float minimumStock() {
    return minimumStock;
  }

}
