package com.oliveira.carrentalapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.carrentalapi.domain.dtos.AuthenticationDto;
import com.oliveira.carrentalapi.domain.dtos.LoginResponseDto;
import com.oliveira.carrentalapi.domain.dtos.UserDto;
import com.oliveira.carrentalapi.domain.models.User;
import com.oliveira.carrentalapi.services.UserService;
import com.oliveira.carrentalapi.services.impl.TokenServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final TokenServiceImpl tokenService;

  public AuthenticationController(AuthenticationManager authenticationManager, UserService userService,
      TokenServiceImpl tokenService) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto data) {

    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

    var auth = this.authenticationManager.authenticate(usernamePassword);

    if (auth.isAuthenticated() == false) {
      throw new BadCredentialsException("Login or Password is invalid!");
    }

    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDto(token));

  }

  @PostMapping("/register")
  public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto data) {

    if (this.userService.findByLogin(data.login()) != null)
      return ResponseEntity.badRequest().build();
    if (data.password() == null)
      return ResponseEntity.badRequest().build();

    /*
     * Allways initialize user role as a normal user
     * For security, save manually in database a user ADMIN
     */
    var newUser = new UserDto(data.login(), data.password());
    this.userService.save(newUser);

    return ResponseEntity.ok().build();

  }

}
