package com.inventory.management.inventory_management.application.service;

import com.inventory.management.inventory_management.domain.category.Category;
import com.inventory.management.inventory_management.domain.category.Category.CategoryFactory;
import com.inventory.management.inventory_management.domain.product.Product;
import com.inventory.management.inventory_management.domain.user.User;
import com.inventory.management.inventory_management.interfaces.dto.product.CreateProductDto;
import com.inventory.management.inventory_management.interfaces.dto.product.ProductResponseDto;
import com.inventory.management.inventory_management.application.exception.product.ProductAlreadyExistsException;
import com.inventory.management.inventory_management.application.exception.user.UserNotFoundException;
import com.inventory.management.inventory_management.infrastructure.model.CategoryModel;
import com.inventory.management.inventory_management.infrastructure.model.ProductModel;
import com.inventory.management.inventory_management.infrastructure.model.StockModel;
import com.inventory.management.inventory_management.infrastructure.model.UserModel;
import com.inventory.management.inventory_management.infrastructure.repository.CategoryRepository;
import com.inventory.management.inventory_management.infrastructure.repository.ProductRepository;
import com.inventory.management.inventory_management.infrastructure.repository.StockRepository;
import com.inventory.management.inventory_management.infrastructure.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final StockRepository stockRepository;
  private final UserRepository userRepository;

  public ProductService(
      ProductRepository productRepository,
      CategoryRepository categoryRepository,
      UserRepository userRepository,
      StockRepository stockRepository
  ) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
    this.userRepository = userRepository;
    this.stockRepository = stockRepository;
  }

  public ProductResponseDto createProduct(
      CreateProductDto createProductDto,
      User user
  ) {

    UserModel userModel = new UserModel(
        user.getName(),
        user.getUserType(),
        user.getEmail()
    );


    CompletableFuture<UserModel> userFuture = CompletableFuture.supplyAsync(() -> this.userRepository.findByEmail(user.getEmail()));
    CompletableFuture<Optional<ProductModel>> productFuture = CompletableFuture.supplyAsync(() -> this.productRepository.findByName(createProductDto.name(), userModel));
    CompletableFuture<List<CategoryModel>> categoriesFuture = CompletableFuture.supplyAsync(() -> {
      assert createProductDto.categoriesId() != null;
      return this.categoryRepository.findAllById(createProductDto.categoriesId(), userModel);
    });
    CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(userFuture, categoriesFuture, productFuture);


    combinedFuture.thenAccept((Void) -> {
      Optional<ProductModel> productModel = productFuture.join();
      UserModel userModelFuture = userFuture.join();
      List<CategoryModel> categoriesModel = categoriesFuture.join();

      if (productModel.isPresent()) {
        throw new ProductAlreadyExistsException(createProductDto.name());
      };

      if (userModelFuture == null) {
        throw new UserNotFoundException(user.getName());
      };

      Set<Category> categories = categoriesModel.stream().map(categoryModel -> {
        CategoryFactory categoryFactory = new Category.CategoryFactory(categoryModel.getName());
        return new Category(categoryFactory);
      }).collect(Collectors.toSet());

      StockModel stockModel = new StockModel();
      stockModel.setAlertOnLowStock(createProductDto.alertOnLowStock());
      stockModel.setMinimumStock(createProductDto.minimumStock());

      Product product = new Product.ProductFactory(UUID.randomUUID().toString(), createProductDto.name())
        .description(createProductDto.description())
        .categories(categories)
        .stock(stockModel)
        .reservationType(createProductDto.reservationType())
        .build();




    });

  }
}
