package com.inventory.management.inventory_management.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Stock", uniqueConstraints = {
    @UniqueConstraint(columnNames = "product_id")
})
public class StockModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @OneToOne
  @JoinColumn(name = "product_id", nullable = false, unique = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ProductModel product;

  // Batch
  @Column(nullable = true, name = "minimum_stock")
  private Float minimumStock;

  @Column(nullable = true, name = "alert_on_low_stock")
  private boolean alertOnLowStock;


  public StockModel() {
  }

  public StockModel(ProductModel product) {
    this.product = product;
  }

  public StockModel(ProductModel product, Float minimumStock, boolean alertOnLowStock) {
    this.product = product;
    this.minimumStock = minimumStock;
    this.alertOnLowStock = alertOnLowStock;
  }

  // Getters and Setters

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProductModel getProduct() {
    return this.product;
  }

  public void setProduct(ProductModel product) {
    this.product = product;
  }

  public Float getMinimumStock() {
    return this.minimumStock;
  }

  public void setMinimumStock(Float minimumStock) {
    this.minimumStock = minimumStock;
  }

  public boolean isAlertOnLowStock() {
    return this.alertOnLowStock;
  }

  public boolean getAlertOnLowStock() {
    return this.alertOnLowStock;
  }

  public void setAlertOnLowStock(boolean alertOnLowStock) {
    this.alertOnLowStock = alertOnLowStock;
  }

}