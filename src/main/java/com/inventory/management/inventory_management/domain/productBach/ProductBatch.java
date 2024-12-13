package com.inventory.management.inventory_management.domain.productBach;

import com.inventory.management.inventory_management.domain.product.Product;
import java.util.Date;

public class ProductBatch {

  private String id;

  private Product product;

  private int quantity;

  private Date expirationDate;

  public ProductBatch(ProductBachFactory productBachFactory) {
    this.id = productBachFactory.id;
    this.product = productBachFactory.product;
    this.quantity = productBachFactory.quantity;
    this.expirationDate = productBachFactory.expirationDate;
  }

  public static class ProductBachFactory {

    private final String id;

    private final Product product;

    private int quantity;

    private Date expirationDate;

    public ProductBachFactory(String id, Product product) {
      this.id = id;
      this.product = product;
    }

    public ProductBachFactory quantity(int quantity) {
      this.quantity = quantity;
      return this;
    }

    public ProductBachFactory expirationDate(Date expirationDate) {
      this.expirationDate = expirationDate;
      return this;
    }

    public ProductBatch build() {
      return new ProductBatch(this);
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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {

    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity must be greater than 0");
    }

    this.quantity = quantity;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {

    Date today = new Date();

    if (expirationDate.before(today)) {
      throw new IllegalArgumentException("Expiration date must be after today");
    }

    this.expirationDate = expirationDate;
  }

}
