package com.inventory.management.inventory_management.domain.category;

import com.inventory.management.inventory_management.domain.product.Product;
import java.util.Set;

public class Category {

  private Long id;

  private String name;

  private Set<Product> products;

  public Category(CategoryFactory categoryFactory) {
    this.name = categoryFactory.name;
    this.products = categoryFactory.products;
  }

  public static class CategoryFactory {

    private final String name;
    private Set<Product> products;

    public CategoryFactory(String name) {
      this.name = name;
    }

    public CategoryFactory products(Set<Product> products) {
      this.products = products;
      return this;
    }

    public Category build() {
      return new Category(this);
    }
  }

  // Getters and Setters

   public String getName() {
    return this.name;
   }

    public void setName(String name) {
      this.name = name;
    }

    public Set<Product> getProducts() {
      return this.products;
    }

    public void setProducts(Set<Product> products) {
      this.products = products;
    }

    public Long getId() {
      return this.id;
    }

    public void setId(Long id) {
      this.id = id;
    }

}
