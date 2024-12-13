package com.inventory.management.inventory_management.domain.stock;

import com.inventory.management.inventory_management.domain.product.PhysicalProduct;
import com.inventory.management.inventory_management.domain.product.Product;

public class PhysicalStock extends Stock {

  private Float quantity;

  public PhysicalStock(StockFactory stockFactory) {
    super(stockFactory);
    this.quantity = stockFactory.quantity;
  }

  public static class StockFactory extends Stock.StockFactory {

    private Float quantity;

    public StockFactory(String id, PhysicalProduct physicalProduct) {
      super(id, physicalProduct);
    }

    public StockFactory quantity(Float quantity) {
      this.quantity = quantity;
      return this;
    }

    @Override
    public PhysicalStock build() {
      return new PhysicalStock(this);
    }
  }

  public Float getQuantity() {
    return quantity;
  }

  public void setQuantity(Float quantity) {
    this.quantity = quantity;
  }

}
