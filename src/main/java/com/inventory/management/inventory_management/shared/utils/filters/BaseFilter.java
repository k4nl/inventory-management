package com.inventory.management.inventory_management.shared.utils.filters;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseFilter<T> implements Filter<T> {

  protected final EntityManager entityManager;

  @Autowired
  public BaseFilter(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

}
