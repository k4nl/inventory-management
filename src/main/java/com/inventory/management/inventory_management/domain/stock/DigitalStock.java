package com.inventory.management.inventory_management.domain.stock;

import com.inventory.management.inventory_management.domain.product.DigitalProduct;
import com.inventory.management.inventory_management.domain.product.Product;

public class DigitalStock extends Stock {

  private String url;

  public DigitalStock(StockFactory stockFactory) {
    super(stockFactory);
    this.url = stockFactory.url;
  }

  public static class StockFactory extends Stock.StockFactory {

    private String url;

    public StockFactory(String id, DigitalProduct digitalProduct) {
      super(id, digitalProduct);
    }

    public StockFactory url(String url) {
      this.url = url;
      return this;
    }

    @Override
    public DigitalStock build() {
      return new DigitalStock(this);
    }
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
