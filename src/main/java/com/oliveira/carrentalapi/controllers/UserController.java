package com.oliveira.carrentalapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.carrentalapi.domain.dtos.UserDto;
import com.oliveira.carrentalapi.domain.mapper.UserMapper;
import com.oliveira.carrentalapi.domain.models.User;
import com.oliveira.carrentalapi.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {

    this.userService = userService;
    this.userMapper = userMapper;

  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {

    List<User> users = userService.findAllUsers();
    List<UserDto> usersDto = new ArrayList<>();

    for (User user : users) {
      usersDto.add(this.userMapper.userToUserDto(user));
    }

    return ResponseEntity.ok().body(usersDto);

  }

  @GetMapping("/me")
  public ResponseEntity<UserDto> getUserLogged() {

    var userAuth = getPrincipal();

    if (userAuth != null) {
      return ResponseEntity.ok().body(new UserDto(userAuth.getLogin(), userAuth.getPassword()));

    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

  }

  @PutMapping
  public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDate) {

    var userAuth = getPrincipal();

    if (userAuth != null) {

      var newUser = new UserDto(userDate.login(), userDate.password());

      userService.update(userAuth.getId(), newUser);

      return ResponseEntity.ok().body(newUser);

    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

  }

  @DeleteMapping
  public ResponseEntity<UserDto> delete() {

    var userAuth = getPrincipal();

    if (userAuth != null) {

      userService.delete(userAuth.getId());

      return ResponseEntity.ok().build();

    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

  }

  private User getPrincipal() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    User newUserAuth = (User) auth.getPrincipal();

    return newUserAuth;

  }

}
