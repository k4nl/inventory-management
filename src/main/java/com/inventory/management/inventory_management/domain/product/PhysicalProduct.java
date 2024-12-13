package com.inventory.management.inventory_management.domain.product;
import com.inventory.management.inventory_management.domain.common.UnitOfMeasure;
import com.inventory.management.inventory_management.domain.productBach.ProductBatch;
import java.time.LocalDate;
import java.util.Set;

public class PhysicalProduct extends Product {

  private final LocalDate expirationDate;
  private final boolean perishable;

  private Set<ProductBatch> batches;

  private UnitOfMeasure unitOfMeasure;

  // Construtor privado para uso exclusivo do builder
  private PhysicalProduct(PhysicalProductFactory physicalProductFactory) {
    super(physicalProductFactory);  // chama o construtor do Product
    this.expirationDate = physicalProductFactory.expirationDate;
    this.perishable = physicalProductFactory.perishable;
    this.batches = physicalProductFactory.batches;
    this.unitOfMeasure = physicalProductFactory.unitOfMeasure;
  }

  // Getters específicos para PhysicalProduct
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public boolean isPerishable() {
    return perishable;
  }

  public Set<ProductBatch> getBatches() {
    return batches;
  }

  public void setBatches(Set<ProductBatch> batches) {
    this.batches = batches;
  }

  public UnitOfMeasure getUnitOfMeasure() {
    return unitOfMeasure;
  }

  public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  // Builder interno para PhysicalProduct
  public static class PhysicalProductFactory extends Product.ProductFactory {
    private LocalDate expirationDate;
    private boolean perishable;

    private Set<ProductBatch> batches;

    private UnitOfMeasure unitOfMeasure;

    public PhysicalProductFactory(String id, String name) {
      super(id, name);
    }

    // Métodos para definir os atributos específicos de PhysicalProduct
    public PhysicalProductFactory expirationDate(LocalDate expirationDate) {
      this.expirationDate = expirationDate;
      return this;
    }

    public PhysicalProductFactory perishable(boolean perishable) {
      this.perishable = perishable;
      return this;
    }

    public PhysicalProductFactory batches(Set<ProductBatch> batches) {
      this.batches = batches;
      return this;
    }

    public PhysicalProductFactory unitOfMeasure(UnitOfMeasure unitOfMeasure) {
      this.unitOfMeasure = unitOfMeasure;
      return this;
    }

    // Método build para retornar a instância de PhysicalProduct
    @Override
    public PhysicalProduct build() {
      return new PhysicalProduct(this);
    }
  }
}
