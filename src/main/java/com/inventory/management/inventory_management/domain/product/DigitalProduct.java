package com.inventory.management.inventory_management.domain.product;
public class DigitalProduct extends Product {

  private final boolean unlimitedStock;

  public DigitalProduct(DigitalProductFactory factory) {
    super(factory);
    this.unlimitedStock = factory.unlimitedStock;
  }

  public boolean isUnlimitedStock() {
    return unlimitedStock;
  }

  public static class DigitalProductFactory extends Product.ProductFactory {
    private boolean unlimitedStock;

    public DigitalProductFactory(String id, String name) {
      super(id, name);
    }

    public DigitalProductFactory unlimitedStock(boolean unlimitedStock) {
      this.unlimitedStock = unlimitedStock;
      return this;
    }

    @Override
    public DigitalProduct build() {
      return new DigitalProduct(this);
    }
  }

}