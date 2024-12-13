package com.inventory.management.inventory_management.infrastructure.repository;

import com.inventory.management.inventory_management.infrastructure.model.CategoryModel;
import com.inventory.management.inventory_management.infrastructure.model.UserModel;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, String> {
    CategoryModel findByName(String name);

    Page<CategoryModel> findAllByUser(UserModel user, Pageable pageable);

    List<CategoryModel> findAllByUser(UserModel user);

    List<CategoryModel> findAllById(List<String> categoriesId, UserModel user);
}
