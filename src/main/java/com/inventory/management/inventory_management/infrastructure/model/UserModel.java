package com.inventory.management.inventory_management.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Set;

@Table(name = "User", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
@Entity
public class UserModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(unique = false, nullable = false, length = 100)
  private final String name;

  @Column(unique = true, nullable = false, length = 100)
  private final String email;

  @Column(nullable = false, name = "user_type")
  private final UserType userType;

  @ManyToOne
  @JoinColumn(name = "merchant_id", nullable = true)
  private UserModel merchant;

  @OneToMany(mappedBy = "merchant")
  private Set<UserModel> operators;

  // Getters and Setters

  public enum UserType {
    ADMIN, MERCHANT, OPERATOR
  }

  public UserModel(String name, UserType userType, String email) {
    this.name = name;
    this.userType = userType;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public Set<UserModel> getOperators() {
    return operators;
  }

  public UserType getUserType() {
    return userType;
  }

  public UserModel getMerchant() {
    return merchant;
  }

  public void setMerchant(UserModel merchant) {
    this.merchant = merchant;
  }
}
