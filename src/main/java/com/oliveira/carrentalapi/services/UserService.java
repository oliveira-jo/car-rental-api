package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.oliveira.carrentalapi.domain.dtos.UserDto;
import com.oliveira.carrentalapi.domain.user.User;

public interface UserService {

  public User insert(UserDto userData);

  public User update(UUID id, UserDto userData);

  public void delete(UUID id);

  public List<User> findAllUsers();

  public UserDetails findByLogin(String login);
}
