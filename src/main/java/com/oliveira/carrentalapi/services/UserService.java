package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.oliveira.carrentalapi.domain.dtos.UserDto;
import com.oliveira.carrentalapi.domain.models.User;

public interface UserService {

  public User save(UserDto userData);

  public User update(UUID id, UserDto userData);

  public void delete(UUID id);

  public List<User> findAllUsers();

  public UserDetails findByLogin(String login);

  public Optional<User> getUserByLogin(String login);

}
