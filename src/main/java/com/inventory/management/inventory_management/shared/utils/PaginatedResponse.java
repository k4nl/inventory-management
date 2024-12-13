package com.inventory.management.inventory_management.shared.utils;

import com.inventory.management.inventory_management.shared.utils.Paginate.PaginationMeta;
import java.util.List;

public class PaginatedResponse<T> {
  private List<T> data;
  private PaginationMeta meta;

  public PaginatedResponse(List<T> data, PaginationMeta meta) {
    this.data = data;
    this.meta = meta;
  }

  // Getters e Setters
}
