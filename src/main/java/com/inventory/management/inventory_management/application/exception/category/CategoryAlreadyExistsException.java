package com.inventory.management.inventory_management.application.exception.category;

public class CategoryAlreadyExistsException extends RuntimeException {
  public CategoryAlreadyExistsException(String categoryName) {
    super("Category with name " + categoryName + " already exists");
  };
}