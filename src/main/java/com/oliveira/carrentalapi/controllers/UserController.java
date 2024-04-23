package com.oliveira.carrentalapi.controllers;

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
import com.oliveira.carrentalapi.domain.user.User;
import com.oliveira.carrentalapi.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {

    this.userService = userService;

  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {

    List<User> users = userService.findAllUsers();

    return ResponseEntity.ok().body(users);

  }

  @GetMapping("/me")
  public ResponseEntity<UserDto> getUserLogged() {

    var userAuth = getPrincipal();

    if (userAuth != null) {
      return ResponseEntity.ok().body(new UserDto(userAuth.getLogin(), userAuth.getPassword(), userAuth.getRole()));

    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

  }

  @PutMapping
  public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDate) {

    var userAuth = getPrincipal();

    if (userAuth != null) {

      var newUser = new UserDto(userDate.login(), userDate.password(), userAuth.getRole());

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
