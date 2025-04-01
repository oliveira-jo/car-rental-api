package com.oliveira.carrentalapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oliveira.carrentalapi.domain.dtos.request.UserRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.UserResponseDto;
import com.oliveira.carrentalapi.domain.enums.UserRole;
import com.oliveira.carrentalapi.domain.exceptions.BusinessException;
import com.oliveira.carrentalapi.domain.exceptions.ObjectNotFoundException;
import com.oliveira.carrentalapi.domain.mapper.UserMapper;
import com.oliveira.carrentalapi.domain.models.User;
import com.oliveira.carrentalapi.repositories.UserRepository;
import com.oliveira.carrentalapi.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public UserResponseDto save(UserRequestDto request) {

    // Test if password and password confirmation is equal and encrypt
    if (!request.password().equals(request.passwordConfirmation()))
      throw new BusinessException("Password and password confirmation are different");

    String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

    // Test if already exist user with this login in database
    String login = request.email().split("@")[0];
    Optional<User> userFromDB = this.userRepository.getUserByLogin(login);

    if (userFromDB.isPresent())
      throw new BusinessException("The user with provide login is already sabedin DataBase: " + login);

    // Create a new user
    User userToBeSaved = new User();

    userToBeSaved.setLogin(login);
    userToBeSaved.setPassword(encryptedPassword);
    userToBeSaved.setRole(UserRole.CLIENT);
    userToBeSaved.setUsername(request.username());
    userToBeSaved.setEmail(request.email());
    userToBeSaved.setPhone(request.phone());
    userToBeSaved.setCnh(request.cnh());
    userToBeSaved.setBirthDate(request.birthDate());

    // Save user in database, converto to dto and retur to controller
    return this.userMapper.toUserDto(
        this.userRepository.save(userToBeSaved));

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public UserResponseDto update(UUID id, UserRequestDto request) {

    // Test if exist user before update
    User userToBeUpdated = this.userRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));

    // Test if password and password confirmation is equal and encrypt
    if (!request.password().equals(request.passwordConfirmation()))
      throw new BusinessException("Password and password confirmation are different");

    // encrypt password
    String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
    // Generate the login from email
    String login = request.email().split("@")[0];

    // don't do all tests because this is make in dto by validation
    // if (!request.getLogin().isEmpty())
    userToBeUpdated.setLogin(login);
    userToBeUpdated.setPassword(encryptedPassword);
    userToBeUpdated.setRole(UserRole.CLIENT);
    userToBeUpdated.setUsername(request.username());
    userToBeUpdated.setEmail(request.email());
    userToBeUpdated.setPhone(request.phone());
    userToBeUpdated.setCnh(request.cnh());
    userToBeUpdated.setBirthDate(request.birthDate());

    // Update user in database, convert to dto and return to controller
    return this.userMapper.toUserDto(
        this.userRepository.save(userToBeUpdated));

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public void delete(UUID id) {
    User user = this.userRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));
    this.userRepository.delete(user);

  }

  public List<UserResponseDto> findAllUsers() {

    return this.userMapper.toUserDto(
        this.userRepository.findAll());

  }

  public UserDetails findByLogin(String login) {

    return this.userRepository.findByLogin(login);

  }

  public UserResponseDto getUserByLogin(String login) {

    return this.userMapper.toUserDto(
        this.userRepository.getUserByLogin(login)
            .orElseThrow(() -> new ObjectNotFoundException("User not found with login: " + login)));

  }

}
