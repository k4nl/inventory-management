package com.inventory.management.inventory_management.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ProductBatch")
public class ProductBatch {

  // Lote de produto
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private ProductModel product;

  @Column(nullable = false)
  private Integer quantity;  // Quantidade no lote

  @Column(nullable = true, name = "expiration_date")
  private Date expirationDate;  // Data de validade (para produtos perecíveis)

  // Getters and Setters


}