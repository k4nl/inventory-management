package com.inventory.management.inventory_management.infrastructure.repository;

import com.inventory.management.inventory_management.domain.user.Merchant;
import com.inventory.management.inventory_management.infrastructure.model.UserModel;
import com.inventory.management.inventory_management.infrastructure.model.UserModel.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
  // Você pode adicionar métodos personalizados se precisar

  UserModel findByEmail(String email);

  Page<UserModel> findByUserType(UserType userType, Pageable pageable);

  Page<UserModel> findByUserType(Merchant merchant, UserType userType, Pageable pageable);
}
