package com.inventory.management.inventory_management.shared.utils.filters;

import com.inventory.management.inventory_management.domain.user.User;
import com.inventory.management.inventory_management.interfaces.dto.user.FilterUserDto;
import com.inventory.management.inventory_management.application.exception.user.UserCannotGetOtherUsersException;
import com.inventory.management.inventory_management.infrastructure.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserFilter extends BaseFilter<UserModel> {

  public UserFilter(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<UserModel> apply(User user, Object filterData) {

    if (!(filterData instanceof FilterUserDto filterUserDto)) {
      throw new IllegalArgumentException("FilterUserDto is expected");
    }

    return switch (user.getUserType()) {
      case ADMIN -> handleAdminFilter(user, filterUserDto);
      case MERCHANT -> handleMerchantFilter(user, filterUserDto);
      default -> throw new UserCannotGetOtherUsersException(user.getName());
    };
  }

  private List<UserModel> handleAdminFilter(User user, FilterUserDto filterUserDto) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<UserModel> query = criteriaBuilder.createQuery(UserModel.class);
    Root<UserModel> root = query.from(UserModel.class);

    List<Predicate> predicates = new ArrayList<>();

    if (filterUserDto.getName() != null) {
      predicates.add(criteriaBuilder.like(root.get("name"), "%" + filterUserDto.getName() + "%"));
    }

    if (filterUserDto.getEmail() != null) {
      predicates.add(criteriaBuilder.like(root.get("email"), "%" + filterUserDto.getEmail() + "%"));
    }

    if (filterUserDto.getUserType() != null) {
      predicates.add(root.get("userType").in(filterUserDto.getUserType()));
    }

    if (filterUserDto.getOperatorIds() != null && !filterUserDto.getOperatorIds().isEmpty()) {
      predicates.add(root.get("operators").in(filterUserDto.getOperatorIds()));
    }

    if (filterUserDto.getMerchantIds() != null && !filterUserDto.getMerchantIds().isEmpty()) {
      predicates.add(root.get("id").in(filterUserDto.getMerchantIds()));
    }

    query.where(predicates.toArray(new Predicate[0]));

    return entityManager.createQuery(query).getResultList();
  }

  private List<UserModel> handleMerchantFilter(User user, FilterUserDto filterUserDto) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<UserModel> query = criteriaBuilder.createQuery(UserModel.class);
    Root<UserModel> root = query.from(UserModel.class);

    List<Predicate> predicates = new ArrayList<>();


    if (filterUserDto.getName() != null) {
      predicates.add(criteriaBuilder.like(root.get("name"), "%" + filterUserDto.getName() + "%"));
    }

    if (filterUserDto.getEmail() != null) {
      predicates.add(criteriaBuilder.like(root.get("email"), "%" + filterUserDto.getEmail() + "%"));
    }

    if (filterUserDto.getOperatorIds() != null && !filterUserDto.getOperatorIds().isEmpty()) {
      predicates.add(criteriaBuilder.equal(root.get("merchantId"), user.getId()));
      predicates.add(root.get("id").in(filterUserDto.getOperatorIds()));
    }

    query.where(predicates.toArray(new Predicate[0]));

    return entityManager.createQuery(query).getResultList();
  }

}
