package com.oliveira.carrentalapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oliveira.carrentalapi.domain.dtos.UserDto;
import com.oliveira.carrentalapi.domain.enums.UserRole;
import com.oliveira.carrentalapi.domain.exceptions.ObjectNotFoundException;
import com.oliveira.carrentalapi.domain.models.User;
import com.oliveira.carrentalapi.repositories.UserRepository;
import com.oliveira.carrentalapi.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User save(UserDto userData) {

    // Test if exist user before create
    if (this.userRepository.findByLogin(userData.login()) != null)
      throw new ObjectNotFoundException("User not found with login: " + userData.login());

    String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());

    User newUser = new User(userData.login(), encryptedPassword, UserRole.USER);

    this.userRepository.save(newUser);

    return newUser;

  }

  @Override
  public User update(UUID id, UserDto userData) {

    // Test if exist user before update
    User newUser = this.userRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));

    // Encryp a new password
    String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());

    if (!newUser.getLogin().isEmpty())
      newUser.setLogin(userData.login());
    if (!newUser.getPassword().isEmpty())
      newUser.setPassword(encryptedPassword);

    this.userRepository.save(newUser);

    return newUser;

  }

  @Override
  public void delete(UUID id) {
    User user = this.userRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));
    this.userRepository.delete(user);

  }

  public List<User> findAllUsers() {
    return this.userRepository.findAll();
  }

  public UserDetails findByLogin(String login) {
    return this.userRepository.findByLogin(login);
  }

  public Optional<User> getUserByLogin(String login) {
    return this.userRepository.getUserByLogin(login);
  }

}
