package com.inventory.management.inventory_management.infrastructure.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(name = "Category", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "name", "user_id" })
})
public class CategoryModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
  private Set<ProductModel> products;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false) // Define a coluna de relacionamento com User
  private UserModel user;

  public CategoryModel() {
  }

  public CategoryModel(String name, UserModel user) {
    this.name = name;
    this.user = user;
  }

  // Getters and Setters

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<ProductModel> getProducts() {
    return this.products;
  }

  public void setProducts(Set<ProductModel> products) {
    this.products = products;
  }

  public UserModel getUser() {
    return this.user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

}