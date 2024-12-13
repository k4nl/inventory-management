package com.inventory.management.inventory_management.application.controller;

import com.inventory.management.inventory_management.domain.user.User;
import com.inventory.management.inventory_management.interfaces.dto.user.CreateMerchantDto;
import com.inventory.management.inventory_management.interfaces.dto.user.CreateOperatorDto;
import com.inventory.management.inventory_management.interfaces.dto.user.CreateUserResponseDto;
import com.inventory.management.inventory_management.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/merchant")
  public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateMerchantDto createUserDto) {
    User user = userService.createMerchant(createUserDto);

    CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto(
        user.getName(),
        user.getEmail(),
        user.getUserType()
    );
    return ResponseEntity.ok(createUserResponseDto);
  }

  @PostMapping("/operator")
  public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateOperatorDto createOperatorDto) {
    User user = userService.createOperator(createOperatorDto);

    CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto(
        user.getName(),
        user.getEmail(),
        user.getUserType()
    );
    return ResponseEntity.ok(createUserResponseDto);
  }

  @GetMapping
  public void getUsers() {
    // Get users
  }



}
