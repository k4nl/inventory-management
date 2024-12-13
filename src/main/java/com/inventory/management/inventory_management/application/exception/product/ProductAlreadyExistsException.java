package com.inventory.management.inventory_management.application.exception.product;

public class ProductAlreadyExistsException extends RuntimeException {

  public ProductAlreadyExistsException(String productName) {
    super("Product with name " + productName + " already exists.");
  };

}
