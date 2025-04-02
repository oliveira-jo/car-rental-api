package com.oliveira.carrentalapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.oliveira.carrentalapi.domain.dtos.request.UserRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.UserResponseDto;
import com.oliveira.carrentalapi.domain.enums.UserRole;
import com.oliveira.carrentalapi.domain.exceptions.BusinessException;
import com.oliveira.carrentalapi.domain.exceptions.ObjectNotFoundException;
import com.oliveira.carrentalapi.domain.mapper.UserMapper;
import com.oliveira.carrentalapi.domain.models.User;
import com.oliveira.carrentalapi.repositories.UserRepository;
import com.oliveira.carrentalapi.services.UserService;

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

    // TEST PASSWORD
    if (!request.password().equals(request.passwordConfirmation()))
      throw new BusinessException("Password and password confirmation are different");

    String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

    // TEST IF USER ALREADY EXIST AND GENERATED LOGIN
    String login = request.email().split("@")[0];
    Optional<User> userFromDB = this.userRepository.getUserByLogin(login);

    if (userFromDB.isPresent())
      throw new BusinessException("The user with provide login is already sabedin DataBase: " + login);

    // CREATE
    User userToBeSaved = new User();
    userToBeSaved.setLogin(login);
    userToBeSaved.setPassword(encryptedPassword);
    userToBeSaved.setRole(UserRole.CLIENT);
    userToBeSaved.setUsername(request.username());
    userToBeSaved.setEmail(request.email());
    userToBeSaved.setPhone(request.phone());
    userToBeSaved.setCnh(request.cnh());
    userToBeSaved.setBirthDate(request.birthDate());

    // SAVE
    return this.userMapper.toUserDto(
        this.userRepository.save(userToBeSaved));

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public UserResponseDto update(UUID userId, UserRequestDto request) {

    // TEST IF USER ALREADY EXIST
    User userToBeUpdated = this.userRepository.findById(userId).orElseThrow(
        () -> new ObjectNotFoundException("User not found with provide id: " + userId));

    // TEST IF IS ADMIN OR SUPPORT
    if (userToBeUpdated.getRole().equals(UserRole.ADMIN))
      throw new BusinessException("User is admin and can't be updated");

    if (userToBeUpdated.getRole().equals(UserRole.SUPPORT))
      throw new BusinessException("User is support and can't be updated");

    // TEST PASSWORD
    if (!request.password().equals(request.passwordConfirmation()))
      throw new BusinessException("Password and password confirmation are different");

    // ENCRYPT PASSWORD AND GENERATE LOGIN
    String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
    String login = request.email().split("@")[0];

    // UPDATE USER
    userToBeUpdated.setLogin(login);
    userToBeUpdated.setPassword(encryptedPassword);
    userToBeUpdated.setRole(UserRole.CLIENT);
    userToBeUpdated.setUsername(request.username());
    userToBeUpdated.setEmail(request.email());
    userToBeUpdated.setPhone(request.phone());
    userToBeUpdated.setCnh(request.cnh());
    userToBeUpdated.setBirthDate(request.birthDate());

    // SAVE UPDATED USER
    return this.userMapper.toUserDto(
        this.userRepository.save(userToBeUpdated));

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public void delete(UUID id) {

    User userToBeUpdated = this.userRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("User not found with provide id: " + id));

    if (userToBeUpdated.getRole().equals(UserRole.ADMIN))
      throw new BusinessException("User is admin and can't be deleted");

    if (userToBeUpdated.getRole().equals(UserRole.SUPPORT))
      throw new BusinessException("User is support and can't be deleted");

    this.userRepository.delete(userToBeUpdated);

  }

  public List<UserResponseDto> findUsers(Authentication auth) {

    User user = (User) auth.getPrincipal();

    if (user.getRole().equals(UserRole.CLIENT)) {
      return List.of(this.userMapper.toUserDto(user));

    } else if (user.getRole().equals(UserRole.ADMIN) || user.getRole().equals(UserRole.SUPPORT)) {
      return this.userMapper.toUserDto(
          this.userRepository.findAll());

    } else {
      throw new BusinessException("User not found with provide id: " + user.getId());
    }

  }

  public UserDetails findByLogin(String login) {

    this.userRepository.getUserByLogin(login)
        .orElseThrow(() -> new ObjectNotFoundException("User not found with login: " + login));

    return this.userRepository.findByLogin(login);

  }

  public UserResponseDto getUserByLogin(String login) {

    this.userRepository.getUserByLogin(login)
        .orElseThrow(() -> new ObjectNotFoundException("User not found with login: " + login));

    return this.userMapper.toUserDto(
        this.userRepository.getUserByLogin(login)
            .orElseThrow(() -> new ObjectNotFoundException("User not found with login: " + login)));

  }

}
