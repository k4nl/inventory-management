package com.inventory.management.inventory_management.shared.utils.filters;

import com.inventory.management.inventory_management.domain.user.User;
import java.util.List;

public interface Filter<T> {
  List<T> apply(User user, Object filterDto);

}