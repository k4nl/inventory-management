package com.inventory.management.inventory_management.application.exception.category;

public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException(String categoryName) {
    super("Category with name " + categoryName + " not found");
  };

  public CategoryNotFoundException() {
    super("Category not found");
  };
}
