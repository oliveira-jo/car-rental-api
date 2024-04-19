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

import com.oliveira.carrentalapi.domain.user.User;
import com.oliveira.carrentalapi.domain.user.UserDto;
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
    List<User> users = this.userService.findAllUsers();
    return ResponseEntity.ok().body(users);

  }

  @PutMapping
  public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDate) {

    try {

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();

      User newUserAuth = (User) auth.getPrincipal();

      var newUser = new UserDto(userDate.login(), userDate.password(), newUserAuth.getRole());

      this.userService.update(newUserAuth.getId(), newUser);

      return ResponseEntity.ok().body(newUser);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

  }

  @DeleteMapping
  public ResponseEntity delete() {

    try {

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();

      User newUserAuth = (User) auth.getPrincipal();

      this.userService.delete(newUserAuth.getId());

      return ResponseEntity.ok().build();

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

  }

}
