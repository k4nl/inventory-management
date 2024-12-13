package com.inventory.management.inventory_management.domain.stock;

import com.inventory.management.inventory_management.domain.product.Product;

public class Stock {

  private String id;
  private Product product;

  private Float minimumStock;

  private boolean alertOnLowStock;

  public Stock() {
  }

  public Stock(StockFactory stockFactory) {
    this.id = stockFactory.id;
    this.product = stockFactory.product;
    this.minimumStock = stockFactory.minimumStock;
    this.alertOnLowStock = stockFactory.alertOnLowStock;
  }

  public static class StockFactory {

    private final String id;
    private final Product product;
    private Float minimumStock;

    private boolean alertOnLowStock;

    public StockFactory(String id, Product product) {
      this.id = id;
      this.product = product;
    }

    public StockFactory minimumStock(Float minimumStock) {
      this.minimumStock = minimumStock;
      return this;
    }

    public StockFactory alertOnLowStock(boolean alertOnLowStock) {
      this.alertOnLowStock = alertOnLowStock;
      return this;
    }

    public Stock build() {
      return new Stock(this);
    }
  }

  // Getters and Setters

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Float getMinimumStock() {
    return minimumStock;
  }

  public void setMinimumStock(Float minimumStock) {
    this.minimumStock = minimumStock;
  }

  public boolean isAlertOnLowStock() {
    return alertOnLowStock;
  }

  public void setAlertOnLowStock(boolean alertOnLowStock) {
    this.alertOnLowStock = alertOnLowStock;
  }

}
