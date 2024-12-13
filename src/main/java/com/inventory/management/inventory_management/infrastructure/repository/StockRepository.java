package com.inventory.management.inventory_management.infrastructure.repository;

import com.inventory.management.inventory_management.infrastructure.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<UserModel, String> {
  // Você pode adicionar métodos personalizados se precisar
}
