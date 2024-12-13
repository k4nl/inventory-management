package com.inventory.management.inventory_management.infrastructure.repository;

import com.inventory.management.inventory_management.infrastructure.model.ProductModel;
import com.inventory.management.inventory_management.infrastructure.model.UserModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, String> {

  Optional<ProductModel> findByName(String name, UserModel user);
}
