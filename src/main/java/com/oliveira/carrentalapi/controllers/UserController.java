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

import com.oliveira.carrentalapi.domain.dtos.request.UserRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.UserResponseDto;
import com.oliveira.carrentalapi.domain.models.User;
import com.oliveira.carrentalapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user", produces = { "application/json" })
@Tag(name = "UserController")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Get All User - Just for admin", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @GetMapping(value = "/all")
  public ResponseEntity<List<UserResponseDto>> getAllUsers() {

    return ResponseEntity.ok().body(
        userService.findAllUsers());

  }

  @Operation(summary = "Get User Logged in the present section", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @GetMapping(value = "/me")
  public ResponseEntity<UserResponseDto> getUserLogged() {

    var userAuth = getPrincipal();
    if (userAuth == null)
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    return ResponseEntity.ok().body(
        this.userService.getUserByLogin(userAuth.getLogin()));

  }

  @Operation(summary = "Update a user", method = "PUT")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "404", description = "Not Found in the System"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @PutMapping
  public ResponseEntity<UserResponseDto> update(@RequestBody @Valid UserRequestDto request) {

    var userAuth = getPrincipal();
    if (userAuth == null)
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    return ResponseEntity.ok().body(
        this.userService.update(userAuth.getId(), request));
  }

  @Operation(summary = "Delete a user", method = "DELETE")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "401", description = "Unauthenticated User"),
      @ApiResponse(responseCode = "500", description = "Server Internal Error"),
  })
  @DeleteMapping
  public ResponseEntity<Void> delete() {

    var userAuth = getPrincipal();
    if (userAuth == null)
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    this.userService.delete(userAuth.getId());

    return ResponseEntity.ok().build();

  }

  private User getPrincipal() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    User newUserAuth = (User) auth.getPrincipal();

    return newUserAuth;

  }

}
