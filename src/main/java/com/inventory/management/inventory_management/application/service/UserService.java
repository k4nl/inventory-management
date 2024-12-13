package com.inventory.management.inventory_management.application.service;

import com.inventory.management.inventory_management.domain.user.Merchant;
import com.inventory.management.inventory_management.domain.user.Operator;
import com.inventory.management.inventory_management.shared.utils.PaginatedResponse;
import com.inventory.management.inventory_management.interfaces.dto.user.CreateMerchantDto;
import com.inventory.management.inventory_management.interfaces.dto.user.CreateOperatorDto;
import com.inventory.management.inventory_management.application.exception.user.UserAlreadyExistsException;
import com.inventory.management.inventory_management.application.exception.user.UserNotFoundException;
import com.inventory.management.inventory_management.infrastructure.model.UserModel;
import com.inventory.management.inventory_management.infrastructure.model.UserModel.UserType;
import com.inventory.management.inventory_management.infrastructure.repository.UserRepository;
import com.inventory.management.inventory_management.shared.utils.Paginate;
import com.inventory.management.inventory_management.shared.utils.Paginate.PaginationMeta;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Merchant createMerchant(CreateMerchantDto createMerchantDto) {
    // Find user by name
    UserModel userModel = this.userRepository.findByEmail(createMerchantDto.name());
    // If user exists, throw exception

    if (userModel != null) {
      throw new UserAlreadyExistsException(createMerchantDto.name());
    };

    // Save user into database

    UserModel merchant = new UserModel(
        createMerchantDto.name(),
        UserType.MERCHANT,
        createMerchantDto.email()
    );

    UserModel merchantCreated = this.userRepository.save(merchant);

    return new Merchant(
        merchantCreated.getId(),
        merchantCreated.getName(),
        merchantCreated.getEmail()
    );
  }

  public Operator createOperator(CreateOperatorDto createOperatorDto) {
    // Find user by name
    UserModel userModel = this.userRepository.findByEmail(createOperatorDto.name());
    // If user exists, throw exception

    if (userModel != null) {
      throw new UserAlreadyExistsException(createOperatorDto.name());
    };

    Optional<UserModel> merchant = this.userRepository.findById(createOperatorDto.merchantId());

    if (merchant.isEmpty()) {
      throw new UserNotFoundException();
    }

    // Save user into database

    UserModel operator = new UserModel(
        createOperatorDto.name(),
        UserType.OPERATOR,
        createOperatorDto.email()
    );

    operator.setMerchant(merchant.get());

    UserModel operatorCreated = this.userRepository.save(operator);

    return new Operator(
        operatorCreated.getId(),
        operatorCreated.getName(),
        operatorCreated.getEmail()
    );
  }

  public PaginatedResponse<Operator> getOperators(Paginate<UserModel> paginate, List<UserModel> filter) {
    Pageable pageable = paginate.toPageable();

    Page<UserModel> operatorPage = this.userRepository.findByUserType(UserType.OPERATOR, pageable);

    List<Operator> operators = operatorPage.stream()
        .map(user -> new Operator(user.getId(), user.getName(), user.getEmail()))
        .toList();

    PaginationMeta paginationMeta = paginate.buildMeta(operatorPage);

    return new PaginatedResponse<>(operators, paginationMeta);
  }


  public PaginatedResponse<Merchant> getMerchants(Paginate<UserModel> paginate) {
    Pageable pageable = paginate.toPageable();

    Page<UserModel> operatorPage = this.userRepository.findByUserType(UserType.MERCHANT, pageable);

    List<Merchant> merchants = operatorPage.stream()
        .map(user -> new Merchant(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getOperators().stream().map(operator -> new Operator(
                operator.getId(),
                operator.getName(),
                operator.getEmail()
            )).collect(Collectors.toSet())
        ))
        .toList();

    PaginationMeta paginationMeta = paginate.buildMeta(operatorPage);

    return new PaginatedResponse<>(merchants, paginationMeta);
  }

  public Merchant getMerchantById(String merchantId) {
    // Get user by id

    Optional<UserModel> merchant = this.userRepository.findById(merchantId);

    if (merchant.isEmpty()) {
      throw new UserNotFoundException();
    }

    if (merchant.get().getUserType() != UserType.MERCHANT) {
      throw new UserNotFoundException();
    }

    return new Merchant(
        merchant.get().getId(),
        merchant.get().getName(),
        merchant.get().getEmail(),
        merchant.get().getOperators().stream().map(operator -> new Operator(
            operator.getId(),
            operator.getName(),
            operator.getEmail()
        )).collect(Collectors.toSet())
    );
  }

  public Operator getOperatorById(Merchant merchant, String operatorId) {
    // Get user by id

    Optional<UserModel> operatorModel = this.userRepository.findById(operatorId);

    if (operatorModel.isEmpty()) {
      throw new UserNotFoundException();
    }

    if (operatorModel.get().getUserType() != UserType.OPERATOR) {
      throw new UserNotFoundException();
    }


    Operator operatorDomain =  new Operator(
        operatorModel.get().getId(),
        operatorModel.get().getName(),
        operatorModel.get().getEmail()
    );

    if (operatorDomain.getMerchant() != merchant) {
      throw new UserNotFoundException();
    };

    return operatorDomain;
  }
}
