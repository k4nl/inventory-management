package com.inventory.management.inventory_management.application.service;

import com.inventory.management.inventory_management.domain.user.User;
import com.inventory.management.inventory_management.shared.utils.PaginatedResponse;
import com.inventory.management.inventory_management.interfaces.dto.category.CategoryResponseDto;
import com.inventory.management.inventory_management.interfaces.dto.category.CreateCategoryDto;
import com.inventory.management.inventory_management.application.exception.category.CategoryAlreadyExistsException;
import com.inventory.management.inventory_management.application.exception.category.CategoryNotFoundException;
import com.inventory.management.inventory_management.infrastructure.model.CategoryModel;
import com.inventory.management.inventory_management.infrastructure.model.UserModel;
import com.inventory.management.inventory_management.infrastructure.repository.CategoryRepository;
import com.inventory.management.inventory_management.shared.utils.Paginate;
import com.inventory.management.inventory_management.shared.utils.Paginate.PaginationMeta;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoryService {

  @Autowired
  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  };

  public CategoryResponseDto createCategory(CreateCategoryDto createCategoryDto, User user) {
    // Find category by name

    UserModel userModel = new UserModel(
        user.getName(),
        user.getUserType(),
        user.getEmail()
    );

    List<CategoryModel> categoryModel = this.categoryRepository.findAllByUser(userModel);

    if(!categoryModel.isEmpty()) {
      // validate if category name already exists

      for (CategoryModel category : categoryModel) {
        if (category.getName().equals(createCategoryDto.name())) {
          throw new CategoryAlreadyExistsException(createCategoryDto.name());
        }
      };

    };

    // Save category into database

    CategoryModel category = new CategoryModel(
        createCategoryDto.name(),
        userModel
    );

    CategoryModel categoryCreated = this.categoryRepository.save(category);

    return new CategoryResponseDto(categoryCreated.getId(), categoryCreated.getName());
  }

  public CategoryResponseDto getCategory(String categoryId, User user) {
    Optional<CategoryModel> categoryModel = this.categoryRepository.findById(categoryId);

    if (categoryModel.isEmpty()) {
      throw new CategoryNotFoundException();
    }

    if (!Objects.equals(categoryModel.get().getUser().getId(), user.getId())) {
      throw new CategoryNotFoundException();
    }

    return new CategoryResponseDto(categoryModel.get().getId(), categoryModel.get().getName());
  }

  public PaginatedResponse<CategoryResponseDto> getCategories(User user, Paginate<CategoryModel> paginate) {

    Pageable pageable = paginate.toPageable();

    UserModel userModel = new UserModel(
        user.getName(),
        user.getUserType(),
        user.getEmail()
    );

    Page<CategoryModel> categories = this.categoryRepository.findAllByUser(userModel, pageable);

    List<CategoryResponseDto> categoriesList = categories.stream()
        .map(category -> new CategoryResponseDto(category.getId(), category.getName()))
        .toList();

    PaginationMeta paginationMeta = paginate.buildMeta(categories);

    return new PaginatedResponse<>(categoriesList, paginationMeta);
  }

  public void deleteCategory(String categoryId, User user) {
    Optional<CategoryModel> categoryModel = this.categoryRepository.findById(categoryId);

    if (categoryModel.isEmpty()) {
      throw new CategoryNotFoundException();
    }

    if (!Objects.equals(categoryModel.get().getUser().getId(), user.getId())) {
      throw new CategoryNotFoundException();
    }

    this.categoryRepository.delete(categoryModel.get());
  }
}
