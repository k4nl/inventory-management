package com.inventory.management.inventory_management.domain.product;

import com.inventory.management.inventory_management.domain.category.Category;
import com.inventory.management.inventory_management.infrastructure.model.ProductModel.ReservationType;
import com.inventory.management.inventory_management.infrastructure.model.StockModel;
import java.util.Set;

public class Product {

  private String id;
  private String name;
  private String description;
  private Set<Category> categories;

  private ReservationType reservationType;

  private StockModel stock;

  public Product (ProductFactory productFactory) {
    this.id = productFactory.id;
    this.name = productFactory.name;
    this.description = productFactory.description;
    this.categories = productFactory.categories;
    this.stock = productFactory.stock;
    this.reservationType = productFactory.reservationType;
  }

  public static class ProductFactory {

    private final String id;
    private final String name;
    private String description;
    private Set<Category> categories;
    private StockModel stock;

    private ReservationType reservationType;

    public ProductFactory(String id, String name) {
      this.id = id;
      this.name = name;
    }

    public ProductFactory description(String description) {
      this.description = description;
      return this;
    }

    public ProductFactory categories(Set<Category> categories) {
      this.categories = categories;
      return this;
    }

    public ProductFactory stock(StockModel stock) {
      this.stock = stock;
      return this;
    }

    public ProductFactory reservationType(ReservationType reservationType) {
      this.reservationType = reservationType;
      return this;
    }

    public Product build() {
      return new Product(this);
    }
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  public StockModel getStock() {
    return stock;
  }

  public void setStock(StockModel stock) {
    this.stock = stock;
  }


  public ReservationType getReservationType() {
    return reservationType;
  }

  public void setReservationType(ReservationType reservationType) {
    this.reservationType = reservationType;
  }
}
