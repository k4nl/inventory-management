package com.inventory.management.inventory_management.infrastructure.model;
import com.inventory.management.inventory_management.domain.category.Category;
import jakarta.persistence.*;
import java.util.Set;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Product", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "name", "user_id" })
})
public class ProductModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false, name = "name")
  private String name;

  @Column(nullable = true, name = "description")
  private String description;

  @Column(nullable = true, name = "unit_of_measure")
  @Enumerated(EnumType.STRING)
  private UnitOfMeasure unitOfMeasure;

  @Column(nullable = false, name = "is_unlimited")
  private boolean unlimited;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "reservation_type")
  private ReservationType reservationType;

  @OneToOne(mappedBy = "product")
  private StockModel stock;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserModel user;

  @ManyToMany
  @JoinTable(
      name = "product_category",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<Category> categories;

  @OneToMany(mappedBy = "product")
  private Set<ProductBatch> batches;

  public enum ReservationType {
    RESERVABLE,
    NON_RESERVABLE
  }

  public enum UnitOfMeasure {
    KILOGRAM,
    GRAM,
    LITER,
    MILLILITER,
    METER,
    CENTIMETER,
    UNIT,
    INCH,

  }

  public ProductModel(
      String name,
      String description,
      UnitOfMeasure unitOfMeasure,
      boolean unlimited,
      ReservationType reservationType,
      UserModel user
  ) {
    this.name = name;
    this.description = description;
    this.unitOfMeasure = unitOfMeasure;
    this.unlimited = unlimited;
    this.reservationType = reservationType;
    this.user = user;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  public void setUnlimited(boolean unlimited) {
    this.unlimited = unlimited;
  }

  public void setReservationType(ReservationType reservationType) {
    this.reservationType = reservationType;
  }

  public void setStock(StockModel stock) {
    this.stock = stock;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  public void setBatches(Set<ProductBatch> batches) {
    this.batches = batches;
  }



  // Getters and Setters



}
